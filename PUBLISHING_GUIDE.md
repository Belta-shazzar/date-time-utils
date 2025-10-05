# Guide to Publishing to Maven Central

## Step 1: Setup Sonatype Account

1. Go to https://issues.sonatype.org/
2. Create a JIRA account
3. Create a new issue to claim your groupId (e.g., com.yourname)
    - Project: Community Support - Open Source Project Repository Hosting
    - Issue Type: New Project
4. Wait for approval (usually 1-2 business days)

## Step 2: Generate GPG Keys

```bash
# Generate key
gpg --gen-key

# List keys to get the key ID
gpg --list-keys

# Export public key to key server
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID

# Export secret key
gpg --export-secret-keys YOUR_KEY_ID > secring.gpg
```

## Step 3: Configure Gradle Properties

Create `~/.gradle/gradle.properties`:

```properties
ossrhUsername=your-jira-username
ossrhPassword=your-jira-password

signing.keyId=last-8-chars-of-your-key-id
signing.password=your-key-password
signing.secretKeyRingFile=/path/to/secring.gpg
```

## Step 4: Publish

```bash
# Build and test
./gradlew clean build test

# Publish to staging
./gradlew publish

# Go to https://s01.oss.sonatype.org/#stagingRepositories
# Find your repository, close it, then release it
```

## Step 5: Alternative - Publish to GitHub Packages (Easier)

Update `build.gradle`:

```gradle
publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/yourusername/datetime-utils")
            credentials {
                username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
```

Then publish:
```bash
./gradlew publish
```

## Step 6: Test Your Published Library

```gradle
repositories {
    mavenCentral()
    // or for GitHub Packages
    maven {
        url = uri("https://maven.pkg.github.com/yourusername/datetime-utils")
    }
}

dependencies {
    implementation 'com.yourname:datetime-utils:1.0.0'
}
```