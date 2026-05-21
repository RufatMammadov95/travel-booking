pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = credentials('docker-registry-namespace')
        DOCKER_CREDENTIALS_ID = 'docker-registry-credentials'
        DEPLOY_SSH_CREDENTIALS_ID = 'cloud-ssh-key'
        DEPLOY_HOST = credentials('cloud-deploy-host')
        DEPLOY_USER = credentials('cloud-deploy-user')
        DEPLOY_PATH = '/opt/travel-booking'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
                archiveArtifacts artifacts: '**/target/site/jacoco/**', allowEmptyArchive: true
     
            }
        }

        stage('Docker Build') {
            steps {
                sh 'DOCKER_REGISTRY=$DOCKER_REGISTRY IMAGE_TAG=$IMAGE_TAG docker-compose build'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
                    sh 'DOCKER_REGISTRY=$DOCKER_REGISTRY IMAGE_TAG=$IMAGE_TAG docker-compose push'
                }
            }
        }

        stage('Deploy to Cloud') {
            steps {
                sshagent(credentials: ["${DEPLOY_SSH_CREDENTIALS_ID}"]) {
                    sh 'ssh -o StrictHostKeyChecking=no "$DEPLOY_USER@$DEPLOY_HOST" "mkdir -p $DEPLOY_PATH"'
                    sh 'scp docker-compose.prod.yml "$DEPLOY_USER@$DEPLOY_HOST:$DEPLOY_PATH/docker-compose.yml"'
                    sh 'ssh "$DEPLOY_USER@$DEPLOY_HOST" "cd $DEPLOY_PATH && DOCKER_REGISTRY=$DOCKER_REGISTRY IMAGE_TAG=$IMAGE_TAG docker compose pull && DOCKER_REGISTRY=$DOCKER_REGISTRY IMAGE_TAG=$IMAGE_TAG docker compose up -d"'
                }
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline finished successfully!'
        }
        failure {
            echo 'Pipeline failed! Please check the Maven tests or Docker logs.'
        }
    }
}
