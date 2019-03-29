#!/bin/bash

JDK="oraclejdk8"

TYPE="Javadoc"

# Print a message
e() {
    echo -e "$1"
}

# Skip the publication with the reason
skip() {
    local skipMessage="Skipping $TYPE publication"

    if [[ $# -ne 0 ]]; then
        skipMessage="$skipMessage: $1"
    fi

    e "$skipMessage"
    exit 1
}

# Check that the context is valid for publication
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
}

# Generate artifacts
generate() {
    e "Generating $TYPE..."

    mvn -q -B javadoc:javadoc javadoc:aggregate -DreportOutputDirectory=$1 -P "deploy-javadoc"

    # Check the generation
    if ! [[ -d $1 ]]; then
        skip "No $TYPE has been generated"
    fi
}

# Clone the publication branch
cloneBranch() {
    if ! [[ -d $1 ]]; then
        e "Cloning '$1' branch..."

        git config --global user.email "travis@travis-ci.org"
        git config --global user.name "travis-ci"
        git clone --quiet --branch=$1 https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG} $1
    fi
}

# Merge the resulting artifacts, and replace the existing ones
mergeIntoBranch() {
    e "Merging $TYPE..."

    # Remove existing artifacts
    if [[ -d $2 ]]; then
        git rm --quiet -rf $2
    fi

    # Copy new artifacts
    mkdir -p $2
    cp -Rfp $1/* $2

    git add -Af

    # Check differences
    if [[ -z "$(git status --porcelain)" ]]; then
        skip "No change"
    fi
}

# Publish artifacts
publish() {
    local commitMessage="[auto] update the $TYPE from Travis #$TRAVIS_BUILD_NUMBER"

    e "Publishing $TYPE..."

    git commit --quiet -m "$commitMessage"
    git push --quiet -f origin $1

    e "$TYPE published"
}

main() {
    local tmpDir=${HOME}/apidocs

    local branch="gh-pages"
    local branchOutputDir=releases/snapshot/doc

    # Working in the build directory
    checkBuildInfo
    generate ${tmpDir}

    # Working in the home directory
    cd $HOME
    cloneBranch ${branch}

    # Working in branch directory
    cd ${branch}
    mergeIntoBranch ${tmpDir} ${branchOutputDir}
    publish ${branch}
}

main