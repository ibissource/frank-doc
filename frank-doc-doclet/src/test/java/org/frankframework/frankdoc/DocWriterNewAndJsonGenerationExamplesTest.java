/*
Copyright 2021 WeAreFrank!

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.frankframework.frankdoc;

import org.frankframework.frankdoc.model.FrankDocModel;
import org.frankframework.frankdoc.wrapper.FrankClassRepository;
import org.frankframework.frankdoc.wrapper.TestUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.json.JsonObject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import static org.frankframework.frankdoc.Constants.FRANK_DOC_GROUP_VALUES_PACKAGE;

public class DocWriterNewAndJsonGenerationExamplesTest {
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "examples-simple-digester-rules.xml", "simple-appconstants.properties", "org.frankframework.frankdoc.testtarget.examples.simple.Start", "simple.xsd", "simple.json"},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF_ENUM_VALUES_IGNORE_CASE, "examples-simple-digester-rules.xml", "simple-appconstants.properties", "org.frankframework.frankdoc.testtarget.examples.simple.Start", "simpleForCompatibility.xsd", "simple.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "examples-sequence-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.sequence.Master", "sequence.xsd", "sequence.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.config.children.Master", "configChildren.xsd", "configChildren.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.config.children2.Master", "configChildren2.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.config.children.excluded.Master", "configChildrenExclude.xsd", "configChildrenExclude.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "examples-simple-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.deprecated.Master", null, "deprecated.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.deprecated.enumValue.Master", "deprecatedEnumValueStrict.xsd", "deprecatedEnumValue.json"},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF_ENUM_VALUES_IGNORE_CASE, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.deprecated.enumValue.Master", "deprecatedEnumValueCompatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.compatibility.fortype.Start", "compatibility-test-expected-strict.xsd", null},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.compatibility.fortype.Start", "compatibility-test-expected-compatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.config.child.no.i.Master", "configChildNoI.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.compatibility.multiple.Start", "compatibility-multiple-test-expected-strict.xsd", null},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.compatibility.multiple.Start", "compatibility-multiple-test-expected-compatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.textconfig.Start", "textconfig-expected.xsd", "textconfig-expected.json"},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.textconfig.Start", "textconfig-expected-compatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.textconfig.plural.Start", "textconfig-expected-strict-plural.xsd", null},
			// Tests that GroupCreator considers parent elements that only reject attributes, not introduce them.
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.exclude.Master", "exclude.xsd", "exclude.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.type.defaultElement.Master", "withDefault.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.highest.commonInterface.Master", "highestCommonInterface.xsd", "highestCommonInterface.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.parameters.Master", null, "parameters.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.plural.config.defaultClassname.Master", "testPluralConflictDefaultOption.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "multiword-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.pattern.violation.A", "testDigesterRulesViolations-strict.xsd", "testDigesterRulesViolations.json"},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "multiword-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.pattern.violation.A", "testDigesterRulesViolations-compatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "multiword-digester-rules-root.xml", null, "org.frankframework.frankdoc.testtarget.examples.pattern.violation.root.A", null, "testDigesterRulesViolationsRoot.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "multiword-digester-rules-long.xml", null, "org.frankframework.frankdoc.testtarget.examples.pattern.violation.longPattern.A", null, "testDigesterRulesViolationsLong.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "reduced-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.entity.reference.Configuration", "entityReference.xsd", "entityReference.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.element.name.Master", null, "elementNames.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.attributeDefault.Master", "attributeDefault.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.sameEnum.only.once.Master", "enumOnlyOnce.xsd", null},
			// Also tests tags.
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.forwards.Master", null, "forwards.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.parent.without.attributes.Master", "handleParentOnlyExcludedAttributes.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.mandatory.multiple.Master", "mandatoryMultiple.xsd", "mandatoryMultiple.json"},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.mandatory.multiple.Master", "mandatoryMultipleCompatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "singular-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.mandatory.single.Master", "mandatorySingle.xsd", "mandatorySingle.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.unsafe.Unsafe", "unsafe.xsd", "unsafe.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.inheritdoc.Child", "inheritdoc.xsd", "inheritdoc.json"},
			// Classes in package "org.frankframework.frankdoc.testtarget.examples.simple.name.conflict.second" are also added although that package is not shown in this table.
			// See method getAllRequiredPackages().
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.simple.name.conflict.first.Master", "nameConflictStrict.xsd", "nameConflict.json"},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.simple.name.conflict.first.Master", "nameConflictCompatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.making.mandatory.reintroduces.Master", "makingMandatoryReintroduces.xsd", "makingMandatoryReintroduces.json"},
			// Test issue: Cannot resolve the name 'MailListenerCumulativeAttributeGroup' to a(n) 'attribute group' component #101.
			// When a FrankElement rejects attributes or config children without creating new config children or groups, AncestorChildNavigation
			// still references its declared and cumulative groups. Therefore such a parent should still be processed to produce groups.
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.rejecting.group.defined.Master", "dontForgetRejectingParents.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.ignore.in.compatibility.Master", "ignoreInCompatibilityStrict.xsd", "ignoreInCompatibility.json"},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.ignore.in.compatibility.Master", "ignoreInCompatibilityCompatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.omit.config.childProtected.Master", "omitConfigChildWhenClassProtected.xsd", "omitConfigChildWhenClassProtected.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.reuse.attributes.Master", "reuseAttributes.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.no.reuse.attributes.overloaded.Master", "noReuseAttributesOverloaded.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.reintroduce.Master", "reintroduce.xsd", "reintroduce.json"},
			{XsdVersion.COMPATIBILITY, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.reintroduce.Master", "reintroduceCompatibility.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.labels.Master", null, "labels.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.exclude.from.type.Master", "excludeFromType.xsd", "excludeFromType.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.packageprivate.override.Child", "child.xsd", null},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.featurepackage.Documented", "documented.xsd", "documented.json"},
			{XsdVersion.STRICT, AttributeTypeStrategy.ALLOW_PROPERTY_REF, "general-test-digester-rules.xml", null, "org.frankframework.frankdoc.testtarget.examples.valueSubs.WithValueSubstitutions", "valueSubs.xsd", "valueSubs.json"}
		});
	}

	private String packageOfClasses;

	public void setUp(String startClassName) {
		int idx = startClassName.lastIndexOf(".");
		packageOfClasses = startClassName.substring(0, idx);
	}

	@MethodSource("data")
	@ParameterizedTest(name = "{0}-{1}-{4}-{5}")
	public void testXsd(XsdVersion xsdVersion, AttributeTypeStrategy attributeTypeStrategy, String digesterRulesFileName, String propertyFilePath, String startClassName, String expectedXsdFileName, String expectedJsonFileName) throws Exception {
		setUp(startClassName);
		// Skip testing when filename is defined as null
		if (expectedXsdFileName == null) {
			return;
		}
		FrankDocModel model = createModel(digesterRulesFileName, propertyFilePath, startClassName);
		DocWriterNew docWriter = new DocWriterNew(model, attributeTypeStrategy, "1.2.3-SNAPSHOT");
		docWriter.init(startClassName, xsdVersion);
		String actualXsd = docWriter.getSchema();
		String expectedXsd = TestUtil.getTestFile("/doc/examplesExpected/" + expectedXsdFileName);
		TestUtil.assertEqualsIgnoreCRLF(expectedXsd, actualXsd);
	}

	private FrankDocModel createModel(String digesterRulesFileName, String appConstantsPropertiesFileName, String startClassName) throws Exception {
		String[] requiredPackages = getAllRequiredPackages(packageOfClasses);
		FrankClassRepository classRepository = TestUtil.getFrankClassRepositoryDoclet(requiredPackages);
		URL appConstantsPropertiesUrl = getUrlFromFileName(appConstantsPropertiesFileName);
		return FrankDocModel.populate(getUrlFromFileName(digesterRulesFileName), appConstantsPropertiesUrl, startClassName, classRepository);
	}

	// It would be nice to put this information in the test case table, method data(). That table is quite wide, however.
	// Adding data there would make the table harder to read.
	private String[] getAllRequiredPackages(String originalPackage) {
		if(originalPackage.equals("org.frankframework.frankdoc.testtarget.examples.simple.name.conflict.first")) {
			return new String[] {"org.frankframework.frankdoc.testtarget.examples.simple.name.conflict.first", "org.frankframework.frankdoc.testtarget.examples.simple.name.conflict.second", FRANK_DOC_GROUP_VALUES_PACKAGE};
		} else {
			return new String[] {originalPackage, FRANK_DOC_GROUP_VALUES_PACKAGE};
		}
	}

	private URL getUrlFromFileName(String fileName) throws IOException {
		return TestUtil.resourceAsURL("doc/" + fileName);
	}

	@MethodSource("data")
	@ParameterizedTest(name = "{0}-{1}-{4}-{5}")
	public void testJson(XsdVersion xsdVersion, AttributeTypeStrategy attributeTypeStrategy, String digesterRulesFileName, String propertyFilePath, String startClassName, String expectedXsdFileName, String expectedJsonFileName) throws Exception {
		setUp(startClassName);
		// Skip JSON testing when filename is defined as null
		if (expectedJsonFileName == null) {
			return;
		}
		FrankDocModel model = createModel(digesterRulesFileName, propertyFilePath, startClassName);
		FrankDocJsonFactory jsonFactory = new FrankDocJsonFactory(model, "1.2.3-SNAPSHOT");
		JsonObject jsonObject = jsonFactory.getJson();
		String actual = jsonObject.toString();
		System.out.println(Utils.jsonPretty(actual));
		String expectedJson = TestUtil.getTestFile("/doc/examplesExpected/" + expectedJsonFileName);
		TestUtil.assertJsonEqual("Comparing JSON", expectedJson, actual);
	}
}
