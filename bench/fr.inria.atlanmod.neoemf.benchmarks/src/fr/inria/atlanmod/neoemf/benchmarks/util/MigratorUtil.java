/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.benchmarks.util;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MigratorUtil {

    public static EObject migrate(EObject eObject, EPackage targetEPackage) {
        Map<EObject, EObject> correspondencesMap = new HashMap<>();
        EObject returnEObject = getCorrespondingEObject(correspondencesMap, eObject, targetEPackage);
        copy(correspondencesMap, eObject, returnEObject);
        for (Iterator<EObject> iterator = EcoreUtil.getAllContents(eObject, true); iterator.hasNext(); ) {
            EObject sourceEObject = iterator.next();
            EObject targetEObject = getCorrespondingEObject(correspondencesMap, sourceEObject, targetEPackage);
            copy(correspondencesMap, sourceEObject, targetEObject);
        }
        return returnEObject;
    }

    private static void copy(Map<EObject, EObject> correspondencesMap, EObject sourceEObject, EObject targetEObject) {
        for (EStructuralFeature sourceFeature : sourceEObject.eClass().getEAllStructuralFeatures()) {
            if (sourceEObject.eIsSet(sourceFeature)) {
                EStructuralFeature targetFeature = targetEObject.eClass().getEStructuralFeature(sourceFeature.getName());
                if (sourceFeature instanceof EAttribute) {
                    targetEObject.eSet(targetFeature, sourceEObject.eGet(sourceFeature));
                }
                else { // EReference
                    if (!sourceFeature.isMany()) {
                        targetEObject.eSet(targetFeature, getCorrespondingEObject(correspondencesMap, (EObject) sourceEObject.eGet(targetFeature), targetEObject.eClass().getEPackage()));
                    }
                    else {
                        @SuppressWarnings({"unchecked"})
                        EList<EObject> sourceList = (EList<EObject>) sourceEObject.eGet(sourceFeature);
                        EList<EObject> targetList = new BasicEList<>();
                        for (EObject aSourceList : sourceList) {
                            targetList.add(getCorrespondingEObject(correspondencesMap, aSourceList, targetEObject.eClass().getEPackage()));
                        }
                        targetEObject.eSet(targetFeature, targetList);
                    }
                }
            }
        }
    }

    private static EObject getCorrespondingEObject(Map<EObject, EObject> correspondencesMap, EObject eObject, EPackage ePackage) {
        EObject targetEObject = correspondencesMap.get(eObject);
        if (targetEObject == null) {
            EClass eClass = eObject.eClass();
            EClass targetClass = (EClass) ePackage.getEClassifier(eClass.getName());
            targetEObject = EcoreUtil.create(targetClass);
            correspondencesMap.put(eObject, targetEObject);
        }
        return targetEObject;
    }
}
