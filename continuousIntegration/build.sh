#!/usr/bin/env bash
#
#: Title                                        :  Continuous Integration Build script
#: Description                                  :  This script is responsible for constructing and configuring the build of the app.
#
#: Author                                       : "Quinn Gil" <Quinn.Gil@premera.com>
#: Version                                      : 1.0
#: Date                                         : February 2, 2017
#

##Prints the usage of the orchestrator
usage()
{
    printf "\n"
    printf "usage: bash %s <options>\n" $0
    printf "OPTIONS:\n"
    printf "\t-?\t\tPrints this.\n"
    printf "\t-s\t\tSonarQube Server. This should be the \$(SonarQubeURL) property.\n"
    printf "\t-u\t\tSonarQube Server Token. This should be the \$(SonarQubeToken) property.\n"
    printf "\t-k\t\tSonarQube Project Key. This should be the \$(SonarQubeProjectKey) property.\n"
    printf "\t-n\t\tSonarQube Project Name. This should be the \$(SonarQubeProjectName) property.\n"
    printf "\t-v\t\tVersion Name. This should be built as \$(version.major).\$(version.minor).\n"
    printf "\t-p\t\tProject. This should be the project to build as defined in settings.gradle.\n"
    printf "\t-g\t\tGradle Flags. For debugging. Use as quotes for multiple flags.\n"
    printf "\n\n\n"
}

SONAR_QUBE_URL=
SONAR_QUBE_USER=
SONAR_PROJECT_KEY=
SHORT_PLAN_NAME=
VERSION_NAME_OVERRIDE="0.0.0.0"
BUILD_PROJECT=
GRADLE_FLAGS=
DATE=`date +%Y%m%d`

#Parse the arguments
while getopts "s:u:p:k:n:v:p:g:" OPTION
do
    case ${OPTION} in
        s)
            SONAR_QUBE_URL=${OPTARG}
            ;;
        u)
            SONAR_QUBE_USER=${OPTARG}
            ;;
        k)
            SONAR_PROJECT_KEY=${OPTARG}
            ;;
        n)
            SONAR_PROJECT_NAME=${OPTARG}
            ;;
        v)
            VERSION_NAME_OVERRIDE="${OPTARG}.${DATE}.${BUILD_BUILDID}"
            ;;
        p)
            BUILD_PROJECT=${OPTARG}
            ;;
        g)
            GRADLE_FLAGS=${OPTARG}
            ;;
        ?)
            usage
            exit 0
    esac
done

echo ${VERSION_NAME_OVERRIDE}

if [ -z "$SONAR_QUBE_URL" ]
then
./gradlew clean \
          ${BUILD_PROJECT}:build \
          ${BUILD_PROJECT}:test \
          ${BUILD_PROJECT}:lint \
          jacocoTestReport \
          ${GRADLE_FLAGS}
else
./gradlew -Dsonar.host.url=${SONAR_QUBE_URL} \
          -Dsonar.login=${SONAR_QUBE_USER} \
          -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
          -Dsonar.projectName=${SONAR_PROJECT_NAME} \
          -PversionNameOverride=${VERSION_NAME_OVERRIDE} \
          -Dsonar.scm.disabled=true \
          clean \
          ${BUILD_PROJECT}:build \
          ${BUILD_PROJECT}:test \
          ${BUILD_PROJECT}:lint \
          jacocoTestReport \
          sonarqube \
          ${GRADLE_FLAGS}
fi

if [ "$?" != "0" ]
then
    exit 1
fi