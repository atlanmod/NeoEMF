#!/bin/bash

JDK="oraclejdk8"

TYPE="Maven snapshot"

# Print a message
e() {
    echo -e "$1"
}

# Skip the deployment with the reason
skip() {
    local skipMessage="Skipping $TYPE publication"

    if [[ $# -ne 0 ]]; then
        skipMessage="$skipMessage: $1"
    fi

    e "$skipMessage"
    exit 1
}

# Check that the context is valid for deployment
checkBuildInfo() {
    if [[ "$TRAVIS_JDK_VERSION" != "$JDK" ]]; then
        skip "Wrong JDK. Expected '$JDK' but was '$TRAVIS_JDK_VERSION'"
    elif [[ "$TRAVIS_PULL_REQUEST" != "false" ]]; then
        skip "Was pull request"
    elif [[ "$TRAVIS_BRANCH" != "master" ]]; then
        skip "Wrong branch. Expected 'master' but was '$TRAVIS_BRANCH'"
    elif [[ "$TRAVIS_OS_NAME" != "linux" ]]; then
        skip "Wrong OS. Expected 'linux' but was '$TRAVIS_OS_NAME'"
    fi

    # Check for SNAPSHOT
    local version=$(mvn -B help:evaluate -Dexpression='project.version' $@ | grep -v '\[' | tail -1)

    if [[ "$version" != *"-SNAPSHOT" ]]; then
        skip "Was not snapshot ($version)"
    fi
}

# Deploy the artifacts
deploy() {
    e "Publishing $TYPE..."

    mvn -q -B clean source:jar-no-fork javadoc:jar install deploy --settings=".util/settings.xml" -DskipTests

    e "$TYPE published"
}

main() {
    checkBuildInfo
    deploy
}

main