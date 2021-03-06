/*
 * generated by Xtext 2.18.0.M3
 */
package org.polarsys.capella.scenario.editor.dslscenario.formatter

import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.polarsys.capella.scenario.editor.dslscenario.dsl.Model
import org.polarsys.capella.scenario.editor.dslscenario.dsl.DslPackage
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion
import org.polarsys.capella.scenario.editor.dslscenario.services.DslGrammarAccess
import com.google.inject.Inject
import org.polarsys.capella.scenario.editor.dslscenario.dsl.Actor

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class DslFormatter extends AbstractFormatter2 {
	@Inject extension DslGrammarAccess // remove it if unused

	def dispatch void format(Model it, extension IFormattableDocument document) {
		val begin = regionFor.keyword("scenario")
		val end = regionFor.keyword("}")
		begin.prepend[noSpace]
		interior(begin, end)[indent]
		
		// format actor lines
		scenarioType.participants.forEach[format]
	}

	def dispatch void format(Actor actor, extension IFormattableDocument document) {
		// each actor definition on a separate line
		actor.regionFor.keyword("actor").prepend[oneSpace]
		actor.regionFor.feature(DslPackage.Literals.ACTOR__ID).append[newLine]
	}
}
