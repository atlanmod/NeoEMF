/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option;

import fr.inria.atlanmod.neoemf.data.Configuration;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceSaveTest;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class BlueprintsNeo4jResourceSaveTest extends BlueprintsResourceSaveTest {

    @Test
    public void testSaveGraphNeo4jResourceNeo4jTypeOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.noOption();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.GRAPH_TYPE, BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
        assertConfigurationHasSize(configuration, 2);
    }

    @Test
    public void testSaveGraphNeo4jResourceNoneCacheTypeOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .noCache()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.NONE.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the cache_type properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#CACHE_TYPE}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceWeakCacheTypeOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .weakCache()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.WEAK.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the cache_type properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#CACHE_TYPE}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceSoftCacheTypeOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .softCache()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.SOFT.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the cache_type properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#CACHE_TYPE}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link  #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceStrongCacheTypeOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .strongCache()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.STRONG.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the use_memory_mapped_buffers properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#USE_MEMORY_MAPPED_BUFFERS}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceTrueBooleanUseMemoryMappedBuffersOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .memoryMappedBuffers()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, Boolean.TRUE.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the use_memory_mapped_buffers properties in the configuration file.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#USE_MEMORY_MAPPED_BUFFERS}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceFalseBooleanUseMemoryMappedBuffersOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .memoryMappedBuffers(false)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, Boolean.FALSE.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the strings_mapped_memory properties in the configuration file (with
     * a positive value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#STRINGS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveStringsMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .stringsMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.STRINGS_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property strings_mapped_memory
     * is set with a negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#STRINGS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNegativeStringsMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .stringsMappedBuffer(-64)
                .asMap();

        Throwable thrown = catchThrowable(() -> resource.save(options));
        assertThat(thrown).isInstanceOf(InvalidDataStoreException.class);
    }

    /**
     * Test the existence of the strings_mapped_memory properties in the configuration file (with
     * a value set to 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#STRINGS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullStringsMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .stringsMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.STRINGS_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the arrays_mapped_memory properties in the configuration file (with
     * a positive value).
     * This test use the option {@link BlueprintsNeo4jResourceOptions#ARRAYS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveArraysMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .arraysMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.ARRAYS_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property arrays_mapped_memory
     * is set with a negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#ARRAYS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNegativeArraysMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .arraysMappedBuffer(-64)
                .asMap();

        Throwable thrown = catchThrowable(() -> resource.save(options));
        assertThat(thrown).isInstanceOf(InvalidDataStoreException.class);
    }

    /**
     * Test the existence of the arrays_mapped_memory properties in the configuration file (with
     * a value set to 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#ARRAYS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullArraysMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .arraysMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.ARRAYS_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the nodes_mapped_memory properties in the configuration file (with
     * a positive value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#NODES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveNodesMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .nodesMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.NODES_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property nodes_mapped_memory
     * is set with a negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#NODES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNegativeNodesMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .nodesMappedBuffer(-64)
                .asMap();

        Throwable thrown = catchThrowable(() -> resource.save(options));
        assertThat(thrown).isInstanceOf(InvalidDataStoreException.class);
    }

    /**
     * Test the existence of the nodes_mapped_memory properties in the configuration file (with
     * a value set to 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#NODES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullNodesMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .nodesMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.NODES_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the properties_mapped_memory properties in the configuration file (with
     * a positive value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#PROPERTIES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositivePropertiesMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .propertiesMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property properties_mapped_memory
     * is set with a negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#PROPERTIES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNegativePropertiesMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .propertiesMappedBuffer(-64)
                .asMap();

        Throwable thrown = catchThrowable(() -> resource.save(options));
        assertThat(thrown).isInstanceOf(InvalidDataStoreException.class);
    }

    /**
     * Test the existence of the properties_mapped_memory properties in the configuration file (with
     * a value set to 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#PROPERTIES_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in
     * {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullPropertiesMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .propertiesMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of the relationships_mapped_memory properties in the configuration file (with a positive
     * value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveRelationshipsMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .relationshipsMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property relationships_mapped_memory is set with a
     * negative value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNegativeRelationshipsMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .relationshipsMappedBuffer(-64)
                .asMap();

        Throwable thrown = catchThrowable(() -> resource.save(options));
        assertThat(thrown).isInstanceOf(InvalidDataStoreException.class);
    }

    /**
     * Test the existence of the relationships_mapped_memory properties in the configuration file (with a value set to
     * 0M).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceNullRelationshipsMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .relationshipsMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Test the existence of relationships_mapped_memory and properties_mapped_memory properties in the configuration
     * file (with a positive value).
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveRelationshipMappedMemoryPositivePropertiesMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .relationshipsMappedBuffer(64)
                .propertiesMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, "64M");
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 4);
    }

    /**
     * Test an exception is thrown when the resource is saved if the property properties_mapped_memory is set with a
     * negative value and the property relationships_mapped_memory is set with a positive value.
     * <p>
     * This test use the option {@link BlueprintsNeo4jResourceOptions#RELATIONSHIPS_MAPPED_MEMORY}
     * but does not test it (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link
     * #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourcePositiveRelationshipMappedMemoryNegativePropertiesMappedMemoryOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .relationshipsMappedBuffer(64)
                .propertiesMappedBuffer(-64)
                .asMap();

        Throwable thrown = catchThrowable(() -> resource.save(options));
        assertThat(thrown).isInstanceOf(InvalidDataStoreException.class);
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
     * BlueprintsNeo4jResourceOptions#RELATIONSHIPS_MAPPED_MEMORY} but does not test it (it is
     * done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()})
     * <p>
     * In addition, there is no verification on the
     * OPTIONS_GRAPH_NEO4J_STORE_DIR (it is done in {@link #testSaveGraphNeo4jResourceNeo4jTypeOption()}
     */
    @Test
    public void testSaveGraphNeo4jResourceAllOptionsValid() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.newBuilder()
                .softCache()
                .memoryMappedBuffers()
                .stringsMappedBuffer(64)
                .arraysMappedBuffer(64)
                .nodesMappedBuffer(64)
                .propertiesMappedBuffer(64)
                .relationshipsMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.SOFT.toString());
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, Boolean.TRUE.toString());
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.STRINGS_MAPPED_MEMORY, "64M");
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.ARRAYS_MAPPED_MEMORY, "64M");
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.NODES_MAPPED_MEMORY, "64M");
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, "64M");
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 9);
    }
}
