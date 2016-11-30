#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" = "atlanmod/NeoEMF" ] && \
   [ "$TRAVIS_JDK_VERSION" = "oraclejdk8" ] && \
   [ "$TRAVIS_PULL_REQUEST" = "false" ] && \
   [ "$TRAVIS_BRANCH" = "master" ] && \
   [ "$TRAVIS_OS_NAME" = "linux" ]; then

  echo "Publishing Maven snapshot..."

  mvn clean source:jar javadoc:jar deploy --settings="util/settings.xml" -DskipTests=true

  echo "Maven snapshot published."
fi