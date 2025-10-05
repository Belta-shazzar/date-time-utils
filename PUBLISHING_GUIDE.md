[//]: # (# Guide to Publishing to Maven Central)

[//]: # ()
[//]: # (## Step 1: Setup Sonatype Account)

[//]: # ()
[//]: # (1. Go to https://issues.sonatype.org/)

[//]: # (2. Create a JIRA account)

[//]: # (3. Create a new issue to claim your groupId &#40;e.g., com.yourname&#41;)

[//]: # (    - Project: Community Support - Open Source Project Repository Hosting)

[//]: # (    - Issue Type: New Project)

[//]: # (4. Wait for approval &#40;usually 1-2 business days&#41;)

[//]: # ()
[//]: # (## Step 2: Generate GPG Keys)

[//]: # ()
[//]: # (```bash)

[//]: # (# Generate key)

[//]: # (gpg --gen-key)

[//]: # ()
[//]: # (# List keys to get the key ID)

[//]: # (gpg --list-keys)

[//]: # ()
[//]: # (# Export public key to key server)

[//]: # (gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID)

[//]: # ()
[//]: # (# Export secret key)

[//]: # (gpg --export-secret-keys YOUR_KEY_ID > secring.gpg)

[//]: # (```)

[//]: # ()
[//]: # (## Step 3: Configure Gradle Properties)

[//]: # ()
[//]: # (Create `~/.gradle/gradle.properties`:)

[//]: # ()
[//]: # (```properties)

[//]: # (ossrhUsername=your-jira-username)

[//]: # (ossrhPassword=your-jira-password)

[//]: # ()
[//]: # (signing.keyId=last-8-chars-of-your-key-id)

[//]: # (signing.password=your-key-password)

[//]: # (signing.secretKeyRingFile=/path/to/secring.gpg)

[//]: # (```)

[//]: # ()
[//]: # (## Step 4: Publish)

[//]: # ()
[//]: # (```bash)

[//]: # (# Build and test)

[//]: # (./gradlew clean build test)

[//]: # ()
[//]: # (# Publish to staging)

[//]: # (./gradlew publish)

[//]: # ()
[//]: # (# Go to https://s01.oss.sonatype.org/#stagingRepositories)

[//]: # (# Find your repository, close it, then release it)

[//]: # (```)

[//]: # ()
[//]: # (## Step 5: Alternative - Publish to GitHub Packages &#40;Easier&#41;)

[//]: # ()
[//]: # (Update `build.gradle`:)

[//]: # ()
[//]: # (```gradle)

[//]: # (publishing {)

[//]: # (    repositories {)

[//]: # (        maven {)

[//]: # (            name = "GitHubPackages")

[//]: # (            url = uri&#40;"https://maven.pkg.github.com/yourusername/datetime-utils"&#41;)

[//]: # (            credentials {)

[//]: # (                username = project.findProperty&#40;"gpr.user"&#41; ?: System.getenv&#40;"GITHUB_ACTOR"&#41;)

[//]: # (                password = project.findProperty&#40;"gpr.key"&#41; ?: System.getenv&#40;"GITHUB_TOKEN"&#41;)

[//]: # (            })

[//]: # (        })

[//]: # (    })

[//]: # (})

[//]: # (```)

[//]: # ()
[//]: # (Then publish:)

[//]: # (```bash)

[//]: # (./gradlew publish)

[//]: # (```)

[//]: # ()
[//]: # (## Step 6: Test Your Published Library)

[//]: # ()
[//]: # (```gradle)

[//]: # (repositories {)

[//]: # (    mavenCentral&#40;&#41;)

[//]: # (    // or for GitHub Packages)

[//]: # (    maven {)

[//]: # (        url = uri&#40;"https://maven.pkg.github.com/yourusername/datetime-utils"&#41;)

[//]: # (    })

[//]: # (})

[//]: # ()
[//]: # (dependencies {)

[//]: # (    implementation 'com.yourname:datetime-utils:1.0.0')

[//]: # (})

[//]: # (```)