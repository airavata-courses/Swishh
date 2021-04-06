package com.swishh.ImageStorage;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.swishh.ImageStorage.models.FilesDao;

@SpringBootTest

public class PojoTester {
	private final int count = 5;
	private static final String POJO_PACKAGE = "com.swishh.ImageStorage.models";

	@Test

	public void ensureExpectedPojoCount() {
		List<PojoClass> pojoClasses = PojoClassFactory.getPojoClasses(POJO_PACKAGE, new FilterPackageInfo());
		Affirm.affirmEquals("Classes added / removed?", count, pojoClasses.size());
	}

	@Test
	public void testPojoStructureAndBehavior() {
		Validator validator = ValidatorBuilder.create()
				// Add Rules to validate structure for POJO_PACKAGE
				// See com.openpojo.validation.rule.impl for more ...
				.with(new GetterMustExistRule()).with(new SetterMustExistRule())
				// Add Testers to validate behaviour for POJO_PACKAGE
				// See com.openpojo.validation.test.impl for more ...
				.with(new SetterTester()).with(new GetterTester()).build();

		validator.validate(POJO_PACKAGE, new FilterPackageInfo());
	}

}
