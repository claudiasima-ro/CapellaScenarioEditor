/**
 * generated by Xtext 2.18.0.M3
 */
package org.polarsys.capella.scenario.editor.dslscenario.ui.contentassist;

import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.polarsys.capella.scenario.editor.dslscenario.dsl.Actor;
import org.polarsys.capella.scenario.editor.dslscenario.dsl.Model;
import org.polarsys.capella.scenario.editor.dslscenario.dsl.ScenarioTypeAndParticipants;
import org.polarsys.capella.scenario.editor.dslscenario.dsl.SequenceMessage;
import org.polarsys.capella.scenario.editor.dslscenario.ui.contentassist.AbstractDslProposalProvider;

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
@SuppressWarnings("all")
public class DslProposalProvider extends AbstractDslProposalProvider {
  @Override
  public void completeActor_Name(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    List<String> _propose = this.getPropose();
    for (final String el : _propose) {
      acceptor.accept(this.createCompletionProposal(el, el, null, context));
    }
  }
  
  @Override
  public void completeSequenceMessage_Source(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    EList<EObject> _variablesDefinedBefore2 = this.variablesDefinedBefore2(((Model) model));
    for (final EObject el : _variablesDefinedBefore2) {
      acceptor.accept(this.createCompletionProposal(((Actor) el).getId(), ((Actor) el).getId(), null, context));
    }
  }
  
  @Override
  public void completeSequenceMessage_Target(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    EList<EObject> _variablesDefinedBefore3 = this.variablesDefinedBefore3(((SequenceMessage) model));
    for (final EObject el : _variablesDefinedBefore3) {
      acceptor.accept(this.createCompletionProposal(((Actor) el).getId(), ((Actor) el).getId(), null, context));
    }
  }
  
  public List<String> getPropose() {
    return Arrays.<String>asList("Hello", "World!", "How", "Are", "You");
  }
  
  public EList<EObject> variablesDefinedBefore(final ScenarioTypeAndParticipants sc) {
    return sc.getParticipants();
  }
  
  public EList<EObject> variablesDefinedBefore2(final Model m) {
    return m.getScenarioType().getParticipants();
  }
  
  public EList<EObject> variablesDefinedBefore3(final SequenceMessage seq) {
    EObject _eContainer = seq.eContainer();
    return ((Model) _eContainer).getScenarioType().getParticipants();
  }
}
