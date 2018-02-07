/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.TextElement;

import java.util.Collection;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
class CountTagComments extends AbstractQuery<Integer> {

    @Override
    public Integer apply(Resource resource) {
        Collection<TextElement> result = createOrderedCollection();

        Model model = getRoot(resource);

        for (CompilationUnit cu : model.getCompilationUnits()) {
            for (Comment comment : cu.getCommentList()) {
                if (Javadoc.class.isInstance(comment)) {
                    Javadoc javadoc = Javadoc.class.cast(comment);
                    for (TagElement tag : javadoc.getTags()) {
                        for (ASTNode node : tag.getFragments()) {
                            if (TextElement.class.isInstance(node)) {
                                TextElement text = TextElement.class.cast(node);
                                result.add(text);
                            }
                        }
                    }
                }
            }
        }

        return result.size();
    }
}
