package fr.inria.atlanmod.neoemf.eclipse.ui.action;

import fr.inria.atlanmod.commons.collect.MoreIterables;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

/**
 * An abstract {@link IActionDelegate} that process metamodels.
 */
public abstract class AbstractMetamodelAction implements IActionDelegate {

    /**
     * The current selection.
     */
    private IStructuredSelection currentSelection;

    @Override
    public void run(IAction action) {
        if (isNull(currentSelection)) {
            return;
        }

        // Register all
        MoreIterables.<Object>stream(currentSelection::iterator)
                .filter(IFile.class::isInstance)
                .map(IFile.class::cast)
                .map(file -> file.getFullPath().toOSString())
                .forEach(consume());
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.currentSelection = Optional.ofNullable(selection)
                .filter(s -> !s.isEmpty())
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast)
                .orElse(null);
    }

    protected abstract Consumer<String> consume();
}
