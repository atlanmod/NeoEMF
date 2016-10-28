/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resources;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceSaveTest;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsNeo4jResourceSaveTest extends BlueprintsResourceSaveTest {

    private static final String TEST_FILENAME = "graphNeo4jResourceSaveOptionTestFile";

    /**
     * Used to verify a property added by Blueprints during the graph creation
     */
    private static final String OPTIONS_GRAPH_NEO4J_STORE_DIR = "blueprints.neo4j.conf.store_dir";

    /**
     * Number of properties for an empty Neo4j instance (with no option provided)
     */
    private static final int DEFAULT_PROPERTY_COUNT = 3;

    @Override
    @Before
    public void setUp() {
        this.testFilePath = TEST_FILENAME;
        super.setUp();
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);
    }

    @Test
    public void testSaveGraphNeo4jResourceNeo4jTypeOption() throws IOException, ConfigurationException {
        options.clear();
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);
        /*
         * Check the configuration file contains the store_dir property (computed by blueprints at graph creation)
         */
        assertConfigurationHasEntry(configuration,
                OPTIONS_GRAPH_NEO4J_STORE_DIR,
                testFile.getAbsolutePath());

        assertConfigurationHasSize(configuration, 0);
    }

    @Test
    public void testSaveGraphNeo4jResourceNoneCacheTypeOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.NONE);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.NONE.toString());

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the cache_type properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceWeakCacheTypeOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.WEAK);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.WEAK.toString());

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the cache_type properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceSoftCacheTypeOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.SOFT);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.SOFT.toString());

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the cache_type properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link  #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceStrongCacheTypeOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.STRONG);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.STRONG.toString());

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the use_memory_mapped_buffers properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceTrueUseMemoryMappedBuffersOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS, BlueprintsNeo4jResourceOptions.UseMemoryMappedBuffer.TRUE);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS,
                BlueprintsNeo4jResourceOptions.UseMemoryMappedBuffer.TRUE.toString());

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the use_memory_mapped_buffers properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceTrueBooleanUseMemoryMappedBuffersOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS, true);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS,
                BlueprintsNeo4jResourceOptions.UseMemoryMappedBuffer.TRUE.toString());

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the use_memory_mapped_buffers properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceFalseUseMemoryMappedBuffersOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS, BlueprintsNeo4jResourceOptions.UseMemoryMappedBuffer.FALSE);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS,
                BlueprintsNeo4jResourceOptions.UseMemoryMappedBuffer.FALSE.toString());

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the use_memory_mapped_buffers properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceFalseBooleanUseMemoryMappedBuffersOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS, false);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS,
                BlueprintsNeo4jResourceOptions.UseMemoryMappedBuffer.FALSE.toString());

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the strings_mapped_memory properties in the configuration file (with
     * a positive value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveStringsMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY, "64M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property strings_mapped_memory
     * is set with a negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test(expected = InvalidDataStoreException.class)
    public void testSaveGraphNeo4jResourceNegativeStringsMappedMemoryOption() throws IOException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY, "-64M");
        resource.save(options);
    }

    /**
     * Test the existence of the strings_mapped_memory properties in the configuration file (with
     * a value set to 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullStringsMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY, "0M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY,
                "0M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the arrays_mapped_memory properties in the configuration file (with
     * a positive value).
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveArraysMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY, "64M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property arrays_mapped_memory
     * is set with a negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test(expected = InvalidDataStoreException.class)
    public void testSaveGraphNeo4jResourceNegativeArraysMappedMemoryOption() throws IOException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY, "-64M");
        resource.save(options);
    }

    /**
     * Test the existence of the arrays_mapped_memory properties in the configuration file (with
     * a value set to 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullArraysMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY, "0M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY,
                "0M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the nodes_mapped_memory properties in the configuration file (with
     * a positive value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveNodesMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY, "64M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property nodes_mapped_memory
     * is set with a negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test(expected = InvalidDataStoreException.class)
    public void testSaveGraphNeo4jResourceNegativeNodesMappedMemoryOption() throws IOException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY, "-64");
        resource.save(options);
    }

    /**
     * Test the existence of the nodes_mapped_memory properties in the configuration file (with
     * a value set to 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullNodesMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY, "0M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY,
                "0M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the properties_mapped_memory properties in the configuration file (with
     * a positive value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositivePropertiesMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY, "64M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property properties_mapped_memory
     * is set with a negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test(expected = InvalidDataStoreException.class)
    public void testSaveGraphNeo4jResourceNegativePropertiesMappedMemoryOption() throws IOException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY, "-64M");
        resource.save(options);
    }

    /**
     * Test the existence of the properties_mapped_memory properties in the configuration file (with
     * a value set to 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullPropertiesMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY, "0M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY,
                "0M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of the relationships_mapped_memory properties in the configuration file (with a positive
     * value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveRelationshipsMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY, "64M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property relationships_mapped_memory is set with a
     * negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test(expected = InvalidDataStoreException.class)
    public void testSaveGraphNeo4jResourceNegativeRelationshipsMappedMemoryOption() throws IOException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY, "-64M");
        resource.save(options);
    }

    /**
     * Test the existence of the relationships_mapped_memory properties in the configuration file (with a value set to
     * 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullRelationshipsMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY, "0M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY,
                "0M");

        assertConfigurationHasSize(configuration, 1);
    }

    /**
     * Test the existence of relationships_mapped_memory and properties_mapped_memory properties in the configuration
     * file (with a positive value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveRelationshipMappedMemoryPositivePropertiesMappedMemoryOption() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY, "64M");
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY, "64M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasSize(configuration, 2);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property properties_mapped_memory is set with a
     * negative value and the property relationships_mapped_memory is set with a positive value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test(expected = InvalidDataStoreException.class)
    public void testSaveGraphNeo4jResourcePositiveRelationshipMappedMemoryNegativePropertiesMappedMemoryOption() throws IOException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY, "64M");
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY, "-64M");

        resource.save(options);
    }

    /**
     * Test the existence of all the properties in the configuration file (all options given to the resource are valid).
     * <p>
     * Tested options are:
     * <ul>
     * <li>cache_type</li>
     * <li>use_memory_mapped_buffers</li>
     * <li>strings_mapped_memory</li>
     * <li>arrays_mapped_memory</li>
     * <li>nodes_mapped_memory</li>
     * <li>properties_mapped_memory</li>
     * <li>relationships_mapped_memory</li>
     * </ul>
     * This test use the option {@link
     * BlueprintsNeo4jResourceOptions#OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY} but does not test it (it is
     * done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the
     * OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceAllOptionsValid() throws IOException, ConfigurationException {
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.SOFT);
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS, BlueprintsNeo4jResourceOptions.UseMemoryMappedBuffer.TRUE);
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY, "64M");
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY, "64M");
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY, "64M");
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY, "64M");
        options.put(BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY, "64M");
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists();

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);

        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.SOFT.toString());

        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS,
                BlueprintsNeo4jResourceOptions.UseMemoryMappedBuffer.TRUE.toString());

        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasEntry(configuration,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY,
                "64M");

        assertConfigurationHasSize(configuration, 7);
    }

    private void assertConfigurationHasSize(PropertiesConfiguration configuration, int expectedSize) {
        assertThat(getKeyCount(configuration)).isEqualTo(DEFAULT_PROPERTY_COUNT + expectedSize); // "The number of properties in the configuration file is not consistent with the given options"
    }

    private void assertConfigurationHasEntry(PropertiesConfiguration configuration, String key, String value) {
        assertThat(configuration.containsKey(key)).isTrue();
        assertThat(configuration.getString(key)).isEqualTo(value);
    }
}
