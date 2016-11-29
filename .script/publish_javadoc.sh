#!/bin/bash

repo_name="atlanmod/NeoEMF"
jdk="oraclejdk8"

path="apidocs"
temp_path="$HOME/$path"

if [ "$TRAVIS_REPO_SLUG" = "$repo_name" ] && [ "$TRAVIS_JDK_VERSION" = "$jdk" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ] && [ "$TRAVIS_BRANCH" = "master" ]; then
    echo -e "Publishing Javadoc...\n"

    # Copy the built javadoc
    cp -R target/site/apidocs ${temp_path}
    cd $HOME

    # Clone the 'gh-pages' branch
    git config --global user.email "travis@travis-ci.org"
    git config --global user.name "travis-ci"
    git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG} gh-pages > /dev/null

    # Update the javadoc in 'gh-pages' branch
    cd gh-pages
    git rm -rf ${path}
    cp -Rf ${temp_path} ${path}

    # Commit modifications
    git add -f .
    git commit -m "Update the Javadoc from Travis build #$TRAVIS_BUILD_NUMBER"
    git push -fq origin gh-pages > /dev/null

    echo -e "Published Javadoc.\n"
else
    echo -e "No need to publish the Javadoc.\n";
fi
