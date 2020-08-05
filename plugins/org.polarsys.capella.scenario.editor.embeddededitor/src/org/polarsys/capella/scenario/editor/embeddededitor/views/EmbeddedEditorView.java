/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.scenario.editor.embeddededitor.views;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.scenario.editor.dslscenario.ui.internal.DslscenarioActivator;
import org.polarsys.capella.scenario.editor.dslscenario.ui.provider.DslscenarioProvider;
import org.polarsys.capella.scenario.editor.embeddededitor.actions.XtextEditorActionFactory;

import com.google.inject.Injector;

public class EmbeddedEditorView extends ViewPart {

  /**
   * The ID of the view as specified by the extension.
   */
  public static final String ID = "org.eclipse.xtext.example.domainmodel.embeddededitor.views.EmbeddedEditorView";

  @Inject
  IWorkbench workbench;

  private TableViewer viewer;
  DslscenarioProvider provider;
  private EmbeddedEditorModelAccess model;
  public static EmbeddedEditor eEditor;
  private Scenario associatedScenarioDiagram;
  Composite top;

  public void reloadContent(String str) {
    IXtextDocument document = eEditor.getDocument();
    document.set(str);
  }

  @Override
  public void saveState(IMemento memento) {
    // do nothing
    DslscenarioActivator activator = DslscenarioActivator.getInstance();
    Injector injector = activator
        .getInjector(DslscenarioActivator.ORG_POLARSYS_CAPELLA_SCENARIO_EDITOR_DSLSCENARIO_DSL);

    EmbeddedEditorFactory factory = injector.getInstance(EmbeddedEditorFactory.class);

    EmbeddedEditor editor = factory.newEditor(provider).withParent(top);
  }

  class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
    @Override
    public String getColumnText(Object obj, int index) {
      return getText(obj);
    }

    @Override
    public Image getColumnImage(Object obj, int index) {
      return getImage(obj);
    }

    @Override
    public Image getImage(Object obj) {
      return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
    }
  }

  @Override
  public void createPartControl(Composite parent) {
    makeActions();

    top = new Composite(parent, SWT.NONE);
    top.setLayout(new GridLayout());

    DslscenarioActivator activator = DslscenarioActivator.getInstance();
    Injector injector = activator
        .getInjector(DslscenarioActivator.ORG_POLARSYS_CAPELLA_SCENARIO_EDITOR_DSLSCENARIO_DSL);

    provider = injector.getInstance(DslscenarioProvider.class);
    EmbeddedEditorFactory factory = injector.getInstance(EmbeddedEditorFactory.class);

    EmbeddedEditor editor = factory.newEditor(provider).withParent(top);
    model = editor.createPartialEditor("", "", "", true);

    eEditor = editor;

  }

  public EmbeddedEditorModelAccess getModel() {
    return model;
  }

  public DslscenarioProvider getProvider() {
    return provider;
  }

  private void makeActions() {
    XtextEditorActionFactory saveAction = new XtextEditorActionFactory();
    saveAction.createSaveAction(this);
  }

  @Override
  public void setFocus() {
    // TODO Auto-generated method stub
  }

  public Scenario getAssociatedScenarioDiagram() {
    return associatedScenarioDiagram;
  }

  public void setAssociatedScenarioDiagram(Scenario scenario) {
    this.associatedScenarioDiagram = scenario;
  }
}
