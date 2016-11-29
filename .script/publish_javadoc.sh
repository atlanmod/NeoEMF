#!/bin/bash

repo_name="atlanmod/NeoEMF"
jdk="oraclejdk8"
path=apidocs
temp_path=$HOME/${path}

if [ "$TRAVIS_REPO_SLUG" = "$repo_name" ] && [ "$TRAVIS_JDK_VERSION" = "$jdk" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ] && [ "$TRAVIS_BRANCH" = "master" ]; then

    if ! [ -d target/site/apidocs ]; then
        echo -e "No new Javadoc.\n"
        exit
    fi

    echo -e "Publishing Javadoc...\n"

    # Copy the built javadoc
    cp -R target/site/apidocs ${temp_path}
    cd $HOME

    # Clone the 'gh-pages' branch
    git config --global user.email "travis@travis-ci.org"
    git config --global user.name "travis-ci"
    git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG} gh-pages

    # Update the javadoc in 'gh-pages' branch
    cd gh-pages

    if [ -d "$path" ]; then
        git rm --quiet -rf ${path}
    fi

    cp -Rf ${temp_path} ${path}

    # Commit modifications
    git add -f .
    git commit --quiet -m "Update the Javadoc from Travis build #$TRAVIS_BUILD_NUMBER"
    git push --quiet -fq origin gh-pages

    echo -e "Published Javadoc.\n"
else
    echo -e "No need to publish the Javadoc.\n";
fi
