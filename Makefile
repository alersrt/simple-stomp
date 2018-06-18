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

clean: gradle.clean

test: gradle.test

build: gradle.build

docs: gradle.docs


##################
# Maven commands #
##################

# Maven command.
#
# Usage:
#	make mvn [task=]
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
	@make gradle task='-x check -x test'

# docs command
gradle.docs:
	@make gradle task='javadoc'

# test command
gradle.test:
	@make gradle task='test \
		jacocoTestReport \
		jacocoTestCoverageVerification'



.PHONY: gradle gradle.clean gradle.test gradle.build gradle.docs
