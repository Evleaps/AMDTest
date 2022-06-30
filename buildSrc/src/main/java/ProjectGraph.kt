import org.gradle.api.Project
import org.gradle.api.logging.Logger
import java.io.File

 /**
 * Creates a project graph for fast lookup by file path
 */
internal class ProjectGraph(project: Project, val gitRoot: File, val logger: Logger? = null) {
    private val rootNode: Node
    private val rootProjectDir: File

    init {
        logger?.info("initializing ProjectGraph")
        rootNode = Node(logger)
        rootProjectDir = project.getSupportRootFolder().canonicalFile
        project.subprojects.forEach {
            logger?.info("creating node for ${it.path}")
            val relativePath = it.projectDir.canonicalFile.toRelativeString(rootProjectDir)
            val sections = relativePath.split(File.separatorChar)
            logger?.info("relative path: $relativePath , sections: $sections")
            val leaf = sections.fold(rootNode) { left, right ->
                left.getOrCreateNode(right)
            }
            leaf.project = it
        }
        logger?.info("finished creating ProjectGraph $rootNode")
    }

    /**
     * Finds the project that contains the given file.
     * The file's path prefix should match the project's path.
     */
    fun findContainingProject(filePath: String): Project? {
        val sections = filePath.split(File.separatorChar)
        val realSections = sections.toMutableList()
        val projectRelativeDir = findProjectRelativeDir()
        for (dir in projectRelativeDir) {
            if (realSections.isNotEmpty() && dir == realSections.first()) {
                realSections.removeAt(0)
            } else {
                break
            }
        }

        logger?.info("finding containing project for $filePath , sections: $realSections")
        return rootNode.find(realSections, 0)
    }

    private fun findProjectRelativeDir(): List<String> {
        return rootProjectDir.toRelativeString(gitRoot).split(File.separatorChar)
    }

    private class Node(val logger: Logger? = null) {
        var project: Project? = null
        private val children = mutableMapOf<String, Node>()

        fun getOrCreateNode(key: String): Node {
            return children.getOrPut(key) {
                Node(
                    logger
                )
            }
        }

        fun find(sections: List<String>, index: Int): Project? {
            logger?.info("finding $sections with index $index in ${project?.path ?: "root"}")
            if (sections.size <= index) {
                logger?.info("nothing")
                return project
            }
            val child = children[sections[index]]
            return if (child == null) {
                logger?.info("no child found, returning ${project?.path ?: "root"}")
                project
            } else {
                child.find(sections, index + 1)
            }
        }
    }
}

/**
 * Returns the path to the canonical root project directory, e.g. {@code frameworks/support}.
 */
fun Project.getSupportRootFolder(): File = project.rootDir
