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
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsTest;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link BlueprintsNeo4jOptions}.
 */
public class BlueprintsNeo4jOptionsTest extends BlueprintsOptionsTest {

    /**
     * Checks the definition of the {@link BlueprintsResourceOptions#GRAPH_TYPE} option, with the Neo4j type.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testNeo4jGraphTypeOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.noOption();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.GRAPH_TYPE, BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
        assertConfigurationHasSize(configuration, 2);
    }

    /**
     * Checks the existence of the {@code cache_type} property in the configuration file.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testNoneCacheOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .noCache()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.NONE.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code cache_type} property in the configuration file.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testWeakCacheOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .weakCache()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.WEAK.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code cache_type} property in the configuration file.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testSoftCacheOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .softCache()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.SOFT.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code cache_type} property in the configuration file.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testStrongCacheOption() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .strongCache()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.CACHE_TYPE, BlueprintsNeo4jResourceOptions.CacheType.STRONG.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code use_memory_mapped_buffers} property in the configuration file.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testUseMemoryMappedBuffersOptionWithTrue() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .memoryMappedBuffers()
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, Boolean.TRUE.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code use_memory_mapped_buffers} property in the configuration file.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testUseMemoryMappedBuffersOptionWithFalse() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .memoryMappedBuffers(false)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, Boolean.FALSE.toString());
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code strings_mapped_memory} property in the configuration file (with a positive
     * value).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testStringsMappedMemoryOptionWithPositive() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .stringsMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.STRINGS_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks that an exception is thrown when the resource is saved if the property {@code strings_mapped_memory} is
     * set with a negative value.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testStringsMappedMemoryOptionWithNegative() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .stringsMappedBuffer(-64)
                .asMap();

        assertThat(catchThrowable(() -> resource.save(options)))
                .isInstanceOf(InvalidBackendException.class);
    }

    /**
     * Checks the existence of the {@code strings_mapped_memory} property in the configuration file (with a value set to
     * 0M).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testStringsMappedMemoryOptionWithNull() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .stringsMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.STRINGS_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code arrays_mapped_memory} property in the configuration file (with a positive
     * value).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testArraysMappedMemoryOptionWithPositive() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .arraysMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.ARRAYS_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks that an exception is thrown when the resource is saved if the property {@code arrays_mapped_memory} is set
     * with a negative value.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testArraysMappedMemoryOptionWithNegative() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .arraysMappedBuffer(-64)
                .asMap();

        assertThat(catchThrowable(() -> resource.save(options)))
                .isInstanceOf(InvalidBackendException.class);
    }

    /**
     * Checks the existence of the {@code arrays_mapped_memory} property in the configuration file (with a value set to
     * 0M).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testArraysMappedMemoryOptionWithNull() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .arraysMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.ARRAYS_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code nodes_mapped_memory} property in the configuration file (with a positive
     * value).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testNodesMappedMemoryOptionWithPositive() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .nodesMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.NODES_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks that an exception is thrown when the resource is saved if the property {@code property
     * nodes_mapped_memory} is set with a negative value.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testNodesMappedMemoryOptionWithNegative() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .nodesMappedBuffer(-64)
                .asMap();

        assertThat(catchThrowable(() -> resource.save(options)))
                .isInstanceOf(InvalidBackendException.class);
    }

    /**
     * Checks the existence of the {@code nodes_mapped_memory} property in the configuration file (with a value set to
     * 0M).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testNodesMappedMemoryOptionWithNull() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .nodesMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.NODES_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code properties_mapped_memory} property in the configuration file (with a positive
     * value).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testPropertiesMappedMemoryOptionWithPositive() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .propertiesMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks that an exception is thrown when the resource is saved if the property {@code properties_mapped_memory} is
     * set with a negative value.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testPropertiesMappedMemoryOptionWithNegative() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .propertiesMappedBuffer(-64)
                .asMap();

        assertThat(catchThrowable(() -> resource.save(options)))
                .isInstanceOf(InvalidBackendException.class);
    }

    /**
     * Checks the existence of the {@code properties_mapped_memory} property in the configuration file (with a value set
     * to 0M).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testPropertiesMappedMemoryOptionWithNull() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .propertiesMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of the {@code relationships_mapped_memory} property in the configuration file (with a
     * positive value).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testRelationshipsMappedMemoryOptionWithPositive() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .relationshipsMappedBuffer(64)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, "64M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks that an exception is thrown when the resource is saved if the property {@code relationships_mapped_memory}
     * is set with a negative value.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testRelationshipsMappedMemoryOptionWithNegative() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .relationshipsMappedBuffer(-64)
                .asMap();

        assertThat(catchThrowable(() -> resource.save(options)))
                .isInstanceOf(InvalidBackendException.class);
    }

    /**
     * Checks the existence of the {@code relationships_mapped_memory} property in the configuration file (with a value
     * set to 0M).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testRelationshipsMappedMemoryOptionWithNull() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .relationshipsMappedBuffer(0)
                .asMap();

        resource.save(options);

        Configuration configuration = getConfiguration();
        assertConfigurationHasEntry(configuration, BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, "0M");
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Checks the existence of {@code relationships_mapped_memory} and {@code properties_mapped_memory} properties in
     * the configuration file (with a positive value).
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testCombinationOptionsValid() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
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
     * Checks that an exception is thrown when the resource is saved if the property {@code properties_mapped_memory} is
     * set with a negative value and the property {@code relationships_mapped_memory} is set with a positive value.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testCombinationOptionsInvalid() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .relationshipsMappedBuffer(64)
                .propertiesMappedBuffer(-64)
                .asMap();

        assertThat(catchThrowable(() -> resource.save(options)))
                .isInstanceOf(InvalidBackendException.class);
    }

    /**
     * Checks the existence of all the properties in the configuration file (all options given to the resource are
     * valid).
     * <p>
     * Tested options are: <ul> <li>{@code cache_type}</li> <li>{@code use_memory_mapped_buffers}</li> <li>{@code
     * strings_mapped_memory}</li> <li>{@code arrays_mapped_memory}</li> <li>{@code nodes_mapped_memory}</li> <li>{@code
     * properties_mapped_memory}</li> <li>{@code relationships_mapped_memory}</li> </ul>
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testAllOptions() throws IOException {
        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
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
