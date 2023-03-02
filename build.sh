#!/usr/bin/env bash

set -xe

while getopts ":p:" opt; do
  case $opt in
    p) PROFILE="$OPTARG"
    ;;
    \?) echo "Invalid option -$OPTARG" >&2
    ;;
  esac
done

if [[ -n "$PROFILE" ]]
then
    ./mvnw clean package -P ${PROFILE} -Dspring.profiles.active=${PROFILE}
    docker build --build-arg JAR_FILE=target/*.jar -t cities .
    docker-compose up -d
fi

