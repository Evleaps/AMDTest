package git

import GitClient
import Sha

class ForkCommit: CommitShaProvider {
    override fun get(commandRunner: GitClient.CommandRunner): Sha {
        val currentBranch = commandRunner.executeAndParseFirst(CURRENT_BRANCH_CMD)
        println("ROMAN!!!!!!!!!  -> currentBranch: $currentBranch")

        val parentBranch = commandRunner.executeAndParse(SHOW_ALL_BRANCHES_CMD)
            .firstOrNull { !it.contains(currentBranch) && it.contains("*") }
            ?.substringAfter("[")
            ?.substringBefore("]")
            ?.substringBefore("~")
            ?.substringBefore("^")

        println("ROMAN!!!!!!!!!  -> parentBranch: $parentBranch")

        requireNotNull(parentBranch) {
            "Parent branch not found"
        }

        return commandRunner.executeAndParseFirst("git merge-base $currentBranch $parentBranch")
    }

    companion object {
        const val CURRENT_BRANCH_CMD = "git rev-parse --abbrev-ref HEAD"
        const val SHOW_ALL_BRANCHES_CMD = "git show-branch -a"
    }
}