package org.atlanmod.neoemf.io.binary.adapter;

import org.eclipse.emf.ecore.*;

public enum FeatureKind {
    EOBJECT_CONTAINER,
    EOBJECT_CONTAINER_PROXY_RESOLVING,

    EOBJECT,
    EOBJECT_PROXY_RESOLVING,

    EOBJECT_LIST,
    EOBJECT_LIST_PROXY_RESOLVING,

    EOBJECT_CONTAINMENT,
    EOBJECT_CONTAINMENT_PROXY_RESOLVING,

    EOBJECT_CONTAINMENT_LIST,
    EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING,

    BOOLEAN,
    BYTE,
    CHAR,
    DOUBLE,
    FLOAT,
    INT,
    LONG,
    SHORT,
    STRING,

    /**
     * @since 2.7
     */
    DATE,

    /**
     * @since 2.8
     */
    ENUMERATOR,

    DATA,
    DATA_LIST,

    FEATURE_MAP;

    /*
EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING; : EReference, isContainment(), isResolveProxies, isMany()
EOBJECT_CONTAINMENT_PROXY_RESOLVING:  EReference, isContainment(), isResolveProxies, NOT isMany()
EOBJECT_CONTAINMENT_LIST: EReference, isContainment(), NOT isResolveProxies,  isMany()
EOBJECT_CONTAINMENT: EReference, isContainment(), NOT isResolveProxies, NOT isMany()
EOBJECT_CONTAINER_PROXY_RESOLVING: EReference, NOT isContainment(), isContainer(), isResolveProxies()
EOBJECT_CONTAINER: EReference, NOT isContainment(), isContainer(), NOT isResolveProxies()
EOBJECT_LIST_PROXY_RESOLVING: EReference, NOT isContainment(), NOT isContainer(), isResolveProxies(), isMAny()
EOBJECT_PROXY_RESOLVING: EReference, NOT isContainment(), NOT isContainer(), isResolveProxies(), NOT isMAny()
EOBJECT_LIST: EReference, NOT isContainment(), NOT isContainer(), NOT isResolveProxies(), isMany()
EOBJECT: EReference, NOT isContainment(), NOT isContainer(), NOT isResolveProxies(), isMany()
*/

    public static FeatureKind get(EStructuralFeature eStructuralFeature) {
        if (eStructuralFeature instanceof EReference) {
            EReference eReference = (EReference) eStructuralFeature;
            if (eReference.isContainment()) {
                if (eReference.isResolveProxies()) {
                    if (eReference.isMany()) {
                        return EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING;
                    } else {
                        return EOBJECT_CONTAINMENT_PROXY_RESOLVING;
                    }
                } else {
                    if (eReference.isMany()) {
                        return EOBJECT_CONTAINMENT_LIST;
                    } else {
                        return EOBJECT_CONTAINMENT;
                    }
                }
            } else if (eReference.isContainer()) {
                if (eReference.isResolveProxies()) {
                    return EOBJECT_CONTAINER_PROXY_RESOLVING;
                } else {
                    return EOBJECT_CONTAINER;
                }
            } else if (eReference.isResolveProxies()) {
                if (eReference.isMany()) {
                    return EOBJECT_LIST_PROXY_RESOLVING;
                } else {
                    return EOBJECT_PROXY_RESOLVING;
                }
            } else {
                if (eReference.isMany()) {
                    return EOBJECT_LIST;
                } else {
                    return EOBJECT;
                }
            }
        } else {
            EAttribute eAttribute = (EAttribute) eStructuralFeature;
            EDataType eDataType = eAttribute.getEAttributeType();
            String instanceClassName = eDataType.getInstanceClassName();
            if (instanceClassName == "org.eclipse.emf.ecore.util.FeatureMap$Entry") {
                return FEATURE_MAP;
            } else if (eAttribute.isMany()) {
                return DATA_LIST;
            } else if (instanceClassName == "java.lang.String") {
                return STRING;
            } else if (instanceClassName == "boolean") {
                return BOOLEAN;
            } else if (instanceClassName == "byte") {
                return BYTE;
            } else if (instanceClassName == "char") {
                return CHAR;
            } else if (instanceClassName == "double") {
                return DOUBLE;
            } else if (instanceClassName == "float") {
                return FLOAT;
            } else if (instanceClassName == "int") {
                return INT;
            } else if (instanceClassName == "long") {
                return LONG;
            } else if (instanceClassName == "short") {
                return SHORT;
            } else if (instanceClassName == "java.util.Date") {
                return DATE;
            } else if (eDataType instanceof EEnum) {
                return ENUMERATOR;
            } else {
                return DATA;
            }
        }
    }
}
