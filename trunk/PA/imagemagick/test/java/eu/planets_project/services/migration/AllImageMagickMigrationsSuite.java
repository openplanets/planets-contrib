package eu.planets_project.services.migration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import eu.planets_project.services.migration.imagemagick.ImageMagickMigrateTestSuite;

/**
 * Suite to run all tests in the ImageMagickMigrate component.
 * @author Peter Melms (peter.melms@uni-koeln.de)
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( { ImageMagickMigrateTestSuite.class })
public class AllImageMagickMigrationsSuite {}




