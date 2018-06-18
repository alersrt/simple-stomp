###############################
# Common defaults/definitions #
###############################

comma := ,

# Checks two given strings for equality.
eq = $(if $(or $(1),$(2)),$(and $(findstring $(1),$(2)),\
                                $(findstring $(2),$(1))),1)


###########
# Aliases #
###########

run: gradle.bootRun

clean: gradle.clean

test: gradle.test

build: gradle.build

docs: gradle.docs


##################
# Gradle commands #
##################

# Gradle command.
#
# Usage:
#	make gradle [task=]
task ?=

gradle:
	docker run \
		--rm \
		--net=host \
		-v $(PWD):/app \
		-w /app \
		gradle:alpine \
		gradle --no-daemon -g /app/.gradle $(task)

# clean command
gradle.clean:
	@make gradle task='clean'

# build command
gradle.build:
	@make gradle task='build -x check -x test'

# docs command
gradle.docs:
	@make gradle task='javadoc'

# test command
gradle.test:
	@make gradle task='test \
		jacocoTestReport \
		jacocoTestCoverageVerification'

# bootRun command
gradle.bootRun:
	@make gradle task='bootRun'


.PHONY: clean test build docs run \
		gradle gradle.clean gradle.test gradle.build gradle.docs gradle.bootRun
