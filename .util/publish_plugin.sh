#!/bin/bash

SLUG="atlanmod/NeoEMF"
JDK="oraclejdk8"
BRANCH="master"
OS="linux"

API_BRANCH="gh-pages"

API_DIR=releases/snapshot/plugin
GEN_DIR=plugins/eclipse/update/target/repository
TEMP_DIR=${HOME}/repository

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  echo "Skipping update-site publication: wrong repository. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_JDK_VERSION" != "$JDK" ]; then
  echo "Skipping update-site publication: wrong JDK. Expected '$JDK' but was '$TRAVIS_JDK_VERSION'."
elif [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  echo "Skipping update-site publication: was pull request."
elif [ "$TRAVIS_BRANCH" != "$BRANCH" ]; then
  echo "Skipping update-site publication: wrong branch. Expected '$BRANCH' but was '$TRAVIS_BRANCH'."
elif [ "$TRAVIS_OS_NAME" != "$OS" ]; then
  echo "Skipping update-site publication: wrong OS. Expected '$OS' but was '$TRAVIS_OS_NAME'."
else
    echo -e "Generating update-site..."

    mvn -B -q -f plugins/eclipse install &> /dev/null

    if ! [ -d "$GEN_DIR" ]; then
        echo -e "Skipping update-site publication: Update-site has not been built."
        exit
    fi

    cp -Rfp "$GEN_DIR/"* "$TEMP_DIR/"
    cd "$HOME"

    if ! [ -d "$API_BRANCH" ]; then
        echo -e "Cloning '$API_BRANCH' branch..."

        git config --global user.email "travis@travis-ci.org"
        git config --global user.name "travis-ci"
        git clone --quiet --branch=${API_BRANCH} https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG} ${API_BRANCH}
    fi

    echo -e "Merging update-site..."

    cd "$API_BRANCH"

    if [ -d "$API_DIR" ]; then
        git rm --quiet -rf "$API_DIR/"
    fi

    mkdir -p "$API_DIR"
    cp -Rfp "$TEMP_DIR/"* "$API_DIR/"
    cp "updatesite/index.html" "$API_DIR/index.html"

    git add -Af

    echo -e "Checking for differences..."

    if [ -z "$(git status --porcelain)" ]; then
        echo -e "Skipping update-site publication: no change."
        exit
    fi

    echo -e "Publishing update-site..."

    git commit --quiet -m "[auto] update the update-site from Travis #$TRAVIS_BUILD_NUMBER"
    git push --quiet -f origin ${API_BRANCH}

    echo -e "Update-site published."
fi
