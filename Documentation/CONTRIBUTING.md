# Contribution Guide
<hr />
This document outlines the fundamentals for contributing to the project, however, these aren't concrete rules and are open to discussion if you feel they are not fit for purpose.

## Getting Started
### Cloning the Repository
First off, you can fork the repository into your own GitHub account and clone that repository to your local machine. If you're stuck, GitHub's ['Fork a repo'](https://docs.github.com/en/get-started/quickstart/fork-a-repo) documentation has a step-by-step guide on how to do this.

Once you've cloned a fork, you can add the upstream dependency to pull down the latest changes like this:
```bash
git remote add upstream https://github.com/chadrakh/trrecordshop.git
git pull -u upstream main
```

<br />

### Creating a Branch
Branching is an important part of the development process; an appropriately named branched conveys to others the purpose of the branch and the prefix allows us to group branches.

The main branch is protected, therefore we cannot commit directly to it and instead use remote branches and pull requests to modify the codebase.

| Group         | Structure                           | Use Case                             | Example                                   |
|---------------|-------------------------------------|--------------------------------------|-------------------------------------------|
| Documentation | documentation/{documentation-area}  | Changes to project documentation     | `documentation/update-contribution-guide` |
| Chore         | chore/{action}-{task}               | System updates and maintenance tasks | `chore/update-dependency-versions`        |
| Test          | test/{action}-{test-case}           | Creating or modifying tests          | `test/add-data-validation-test`           |
| Feature       | feature/{action}-{{feature-name}    | Adding a new functionality           | `feature/add-service-layer`               |
| Bug Fix       | bugfix/{issue-number}-{description} | Fixing logged issues                 | `bugfix/12-fix-save-bug`                  |
| Release       | release{version-number}             | Preparing to roll-out a new version  | `release/v1.0.0`                          |

<br />

Conventional IDEs have an easy-to-navigate interface, but here are some useful commands for you use the terminal to work with branches.

```md
# List existing branches (q to exit)
git branch

# List existing remote branches (q to exit)
git branch -a

# Switch between existing branches
git switch <branch>

# Create a new branch
git branch <branch>

# Create a new branch and switch to it
# (Without the '-b' flag, the system will try to switch to a non-existent branch)
git checkout -b <branch>

# Delete a branch (safe)
# (Hint: Capitalised flag '-D' will delete a branch even if it has unmerged changes)
git branch -d <branch>

# Rename current branch
git branch -m <branch>

```

<br />

### Submitting a Pull Request
Once you're set up, you've made changes (and written tests for them) and committed them to your branch you can make a [Pull Request](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request-from-a-fork) (PR).

A good pull request describes the changes in further detail, which builds upon the descriptive branch name.

Here are commands for checking changes, staging them (preparing them to be committed), committing them and pushing them up to the remote:

```md
# Step 1 - View current changes
git status

# Step 2 - Stage specific changed file or all changes
git add <file>
git add .

# Step 3 - Commit staged changes within branch
# '-m' flag is for messages (must be followed with quotation marks)
git commit -m "Documentation: Add Contrib Guide"

# Step 4- Push changes upstream (upstream for the specific branch has to be defined)
git push --set-upstream origin <branch>

# (OPTIONAL) Un-stage files
git reset

# (OPTIONAL) Run the command below to let the system automatically setup the remote tracking
# This way you no longer have to set upstream when you push to a remote
git config --global push.autoSetupRemote true
```

*For documentation on Git commands, click [here](https://www.atlassian.com/git/glossary).*