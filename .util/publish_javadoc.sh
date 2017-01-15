#!/bin/bash

SLUG="atlanmod/NeoEMF"
JDK="oraclejdk8"
BRANCH="master"
OS="linux"

API_DIR="doc"
ROOT_API_DIR="releases/snapshot"
TEMP_DIR="$HOME/$API_DIR"

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

    mvn -B -q javadoc:javadoc javadoc:aggregate -P "deploy-javadoc" &> /dev/null

    if ! [ -d target/site/apidocs ]; then
        echo -e "Skipping Javadoc publication: no Javadoc has been generated."
        exit
    fi

    echo -e "Copying Javadoc..."

    cp -rf target/site/apidocs ${TEMP_DIR}
    cd $HOME

    if ! [ -d "gh-pages" ]; then
        echo -e "Cloning 'gh-pages' branch..."

        git config --global user.email "travis@travis-ci.org"
        git config --global user.name "travis-ci"
        git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG} gh-pages
    fi

    echo -e "Merging Javadoc..."

    cd gh-pages

    mkdir -p ${ROOT_API_DIR}
    cd ${ROOT_API_DIR}

    if [ -d "$API_DIR" ]; then
        git rm --quiet -rf ${API_DIR}
    fi

    cp -rf ${TEMP_DIR} ${API_DIR}

    git add -Af

    echo -e "Checking for differences..."

    if [ -z "$(git status --porcelain)" ]; then
        echo -e "Skipping Javadoc publication: no change."
        exit
    fi

    echo -e "Publishing Javadoc..."

    git commit --quiet -m "[auto] update the Javadoc from Travis build $TRAVIS_BUILD_NUMBER"
    git push --quiet -f origin gh-pages

    echo -e "Javadoc published."
fi
