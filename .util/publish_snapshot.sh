#!/bin/bash

SLUG="atlanmod/NeoEMF"
JDK="oraclejdk8"
BRANCH="master"
OS="linux"

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  echo "Skipping snapshot deployment: wrong repository. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_JDK_VERSION" != "$JDK" ]; then
  echo "Skipping snapshot deployment: wrong JDK. Expected '$JDK' but was '$TRAVIS_JDK_VERSION'."
elif [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  echo "Skipping snapshot deployment: was pull request."
elif [ "$TRAVIS_BRANCH" != "$BRANCH" ]; then
  echo "Skipping snapshot deployment: wrong branch. Expected '$BRANCH' but was '$TRAVIS_BRANCH'."
elif [ "$TRAVIS_OS_NAME" != "$OS" ]; then
  echo "Skipping snapshot deployment: wrong OS. Expected '$OS' but was '$TRAVIS_OS_NAME'."
else

    # Get the existing version of the build
    echo "Checking project version..."
    VERSION=$(mvn -B help:evaluate -Dexpression='project.version' $@ | grep -v '\[' | tail -1)

    # Check for SNAPSHOT
    if [[ "$VERSION" != *"-SNAPSHOT" ]]; then
        echo "Skipping snapshot deployment: was not snapshot ($VERSION)."
        exit
    fi

    echo "Publishing Maven snapshot..."

    mvn -B -q clean source:jar-no-fork javadoc:jar install deploy --settings=".util/settings.xml" -DskipTests

    echo "Maven snapshot published."
fi