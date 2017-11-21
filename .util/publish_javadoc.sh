#!/bin/bash

SLUG="atlanmod/NeoEMF"
JDK="oraclejdk8"
BRANCH="master"
OS="linux"

API_BRANCH="gh-pages"

API_DIR=releases/snapshot/doc
TEMP_DIR=${HOME}/apidocs

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  echo "Skipping Javadoc publication: wrong repository. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_JDK_VERSION" != "$JDK" ]; then
  echo "Skipping Javadoc publication: wrong JDK. Expected '$JDK' but was '$TRAVIS_JDK_VERSION'."
elif [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  echo "Skipping Javadoc publication: was pull request."
elif [ "$TRAVIS_BRANCH" != "$BRANCH" ]; then
  echo "Skipping Javadoc publication: wrong branch. Expected '$BRANCH' but was '$TRAVIS_BRANCH'."
elif [ "$TRAVIS_OS_NAME" != "$OS" ]; then
  echo "Skipping Javadoc publication: wrong OS. Expected '$OS' but was '$TRAVIS_OS_NAME'."
else
    echo -e "Generating Javadoc..."

    mvn -B -q javadoc:javadoc javadoc:aggregate -DreportOutputDirectory="$HOME" -P "deploy-javadoc" &> /dev/null

    if ! [ -d "$TEMP_DIR" ]; then
        echo -e "Skipping Javadoc publication: no Javadoc has been generated."
        exit
    fi

    cd "$HOME"

    if ! [ -d "$API_BRANCH" ]; then
        echo -e "Cloning '$API_BRANCH' branch..."

        git config --global user.email "travis@travis-ci.org"
        git config --global user.name "travis-ci"
        git clone --quiet --branch=${API_BRANCH} https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG} ${API_BRANCH}
    fi

    echo -e "Merging Javadoc..."

    cd "$API_BRANCH"

    if [ -d "$API_DIR" ]; then
        git rm --quiet -rf "$API_DIR/"
    fi

    mkdir -p "$API_DIR"
    cp -Rfp "$TEMP_DIR/"* "$API_DIR/"

    git add -Af

    echo -e "Checking for differences..."

    if [ -z "$(git status --porcelain)" ]; then
        echo -e "Skipping Javadoc publication: no change."
        exit
    fi

    echo -e "Publishing Javadoc..."

    git commit --quiet -m "[auto] update the Javadoc from Travis #$TRAVIS_BUILD_NUMBER"
    git push --quiet -f origin ${API_BRANCH}

    echo -e "Javadoc published."
fi
