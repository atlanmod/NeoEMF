package fr.inria.atlanmod.neoemf.test.commons.models.mapSample.util;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.*;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage
 * @generated
 */
public class MapSampleSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static MapSamplePackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MapSampleSwitch() {
        if (modelPackage == null) {
            modelPackage = MapSamplePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case MapSamplePackage.SAMPLE_MODEL: {
                SampleModel sampleModel = (SampleModel)theEObject;
                T result = caseSampleModel(sampleModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.STRING_TO_STRING_MAP: {
                @SuppressWarnings("unchecked") Map.Entry<String, String> stringToStringMap = (Map.Entry<String, String>)theEObject;
                T result = caseStringToStringMap(stringToStringMap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.K: {
                K k = (K)theEObject;
                T result = caseK(k);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.V: {
                V v = (V)theEObject;
                T result = caseV(v);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.KTO_VMAP: {
                @SuppressWarnings("unchecked") Map.Entry<K, V> kToVMap = (Map.Entry<K, V>)theEObject;
                T result = caseKToVMap(kToVMap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.SAMPLE_MODEL_CONTENT_OBJECT: {
                SampleModelContentObject sampleModelContentObject = (SampleModelContentObject)theEObject;
                T result = caseSampleModelContentObject(sampleModelContentObject);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.PACK: {
                Pack pack = (Pack)theEObject;
                T result = casePack(pack);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.ABSTRACT_PACK_CONTENT: {
                AbstractPackContent abstractPackContent = (AbstractPackContent)theEObject;
                T result = caseAbstractPackContent(abstractPackContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.PACK_CONTENT: {
                PackContent packContent = (PackContent)theEObject;
                T result = casePackContent(packContent);
                if (result == null) result = caseAbstractPackContent(packContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.SPECIALIZED_PACK_CONTENT: {
                SpecializedPackContent specializedPackContent = (SpecializedPackContent)theEObject;
                T result = caseSpecializedPackContent(specializedPackContent);
                if (result == null) result = casePackContent(specializedPackContent);
                if (result == null) result = caseAbstractPackContent(specializedPackContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.PACK_CONTENT2: {
                PackContent2 packContent2 = (PackContent2)theEObject;
                T result = casePackContent2(packContent2);
                if (result == null) result = caseAbstractPackContent(packContent2);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case MapSamplePackage.ABSTRACT_PACK_CONTENT_COMMENT: {
                AbstractPackContentComment abstractPackContentComment = (AbstractPackContentComment)theEObject;
                T result = caseAbstractPackContentComment(abstractPackContentComment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sample Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sample Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSampleModel(SampleModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String To String Map</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String To String Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStringToStringMap(Map.Entry<String, String> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>K</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>K</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseK(K object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>V</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>V</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseV(V object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KTo VMap</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KTo VMap</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKToVMap(Map.Entry<K, V> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sample Model Content Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sample Model Content Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSampleModelContentObject(SampleModelContentObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pack</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pack</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePack(Pack object) {
        return null;
    }

				/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Pack Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Pack Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractPackContent(AbstractPackContent object) {
        return null;
    }

                /**
     * Returns the result of interpreting the object as an instance of '<em>Pack Content</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pack Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePackContent(PackContent object) {
        return null;
    }

				/**
     * Returns the result of interpreting the object as an instance of '<em>Specialized Pack Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Specialized Pack Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSpecializedPackContent(SpecializedPackContent object) {
        return null;
    }

                /**
     * Returns the result of interpreting the object as an instance of '<em>Pack Content2</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pack Content2</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePackContent2(PackContent2 object) {
        return null;
    }

                /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Pack Content Comment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Pack Content Comment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractPackContentComment(AbstractPackContentComment object) {
        return null;
    }

                /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //MapSampleSwitch
