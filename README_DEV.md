# Release

1. Change version in POM
2. ``git commit -am "Set version X.Y.Z-Q." && git push origin`` to create actual release commit (replace X.Y.Z-Q by actual version)
3. ``mvn scm:tag`` to tag release commit and thus create a GitHub release (usually only for versions without qualifier)

# Check for dependency updates

Run the following command:

    mvn versions:display-dependency-updates
