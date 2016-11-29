#!/bin/bash

set -e

if [ "$TRAVIS_REPO_SLUG" = "atlanmod/NeoEMF" ] && [ "$TRAVIS_JDK_VERSION" = "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ] && [ "$TRAVIS_BRANCH" = "master" ]; then
    echo -e "Publishing javadoc...\n"

    cp -R target/site/apidocs $HOME/.neoemf/apidocs
    cd $HOME

    git config --global user.email "travis@travis-ci.org"
    git config --global user.name "travis-ci"
    git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG} gh-pages > /dev/null

    cd gh-pages

    git rm -rf ./apidocs
    cp -Rf $HOME/.neoemf/apidocs ./apidocs

    git add -f .
    git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
    git push -fq origin gh-pages > /dev/null

    echo -e "Published Javadoc to gh-pages.\n"
else
    echo -e "No need to publish Javadoc.\n";
fi
