package git

import GitClient
import Sha

class SpecifiedBranchCommit(private val branch: String) : CommitShaProvider {

    override fun get(commandRunner: GitClient.CommandRunner): Sha {
        return commandRunner.executeAndParseFirst("git rev-parse $branch")
    }
}