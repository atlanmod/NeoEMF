/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.examples;

/**
 * A descriptor class that describes where to find the zipped contents of a project and what that project should be
 * named when unzipped into the workspace.
 */
class ProjectDescriptor {

    private static final String BASE_PROJECTS_DIR = "projects/";

    /**
     * Name of the plugin where the zip file is located.
     */
    private final String bundleName;

    /**
     * Location (relative to the bundle root) of the file to extract.
     */
    private final String zipLocation;

    /**
     * Name of the project that should be created when unzipping.
     */
    private final String projectName;

    /**
     * Construct a descriptor that points to a zip file located in a particular bundle at the given location within that
     * bundle. Also provided is the project name for which the zip is the contents. Note that this project name should
     * be the same as is in the contents not some alternative name.
     *
     * @param bundleName    the bundle in the runtime that contains the zipped up project contents
     * @param projectName the project name in the workspace that will be created to house the project contents
     */
    public ProjectDescriptor(String bundleName, String projectName) {
        this(bundleName, projectName, BASE_PROJECTS_DIR + projectName + ".zip");
    }

    /**
     * Construct a descriptor that points to a zip file located in a particular bundle at the given location within that
     * bundle. Also provided is the project name for which the zip is the contents. Note that this project name should
     * be the same as is in the contents not some alternative name.
     *
     * @param bundleName    the bundle in the runtime that contains the zipped up project contents
     * @param projectName the project name in the workspace that will be created to house the project contents
     * @param zipLocation the location within the bundle where the zip file is located
     */
    public ProjectDescriptor(String bundleName, String projectName, String zipLocation) {
        this.bundleName = bundleName;
        this.zipLocation = zipLocation;
        this.projectName = projectName;
    }

    /**
     * Returns the bundle name.
     *
     * @return the bundle name
     */
    public String bundleName() {
        return bundleName;
    }

    /**
     * Returns the project name.
     *
     * @return the project name
     */
    public String projectName() {
        return projectName;
    }

    /**
     * Returns the zip file location.
     *
     * @return the zip file location
     */
    public String zipLocation() {
        return zipLocation;
    }
}
