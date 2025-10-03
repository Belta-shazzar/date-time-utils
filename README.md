# DateTime Utils

A simple and intuitive DateTime utilities library for Java, providing convenient methods for common date and time operations.

## Features

- Date comparison utilities (isToday, isPast, isFuture)
- Date calculations (daysBetween, yearsBetween, calculateAge)
- Business day calculations (nextBusinessDay, isWeekday, isWeekend)
- Human-readable date formatting
- Date range generation
- Month boundary calculations (firstDayOfMonth, lastDayOfMonth)
- Timestamp conversions

## Installation

### Maven
```xml
<dependency>
    <groupId>com.shazzar</groupId>
    <artifactId>datetime-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle
```gradle
implementation 'com.shazzar:datetime-utils:1.0.0'
```

## Usage Examples

```java
import com.yourname.datetimeutils.DateTimeUtils;
import java.time.LocalDate;

// Check if a date is today
boolean today = DateTimeUtils.isToday(LocalDate.now());

// Calculate age
LocalDate birthDate = LocalDate.of(1990, 5, 15);
int age = DateTimeUtils.calculateAge(birthDate);

// Get next business day
LocalDate nextBusiness = DateTimeUtils.nextBusinessDay(LocalDate.now());

// Format date to readable string
String readable = DateTimeUtils.toReadableString(LocalDate.now());
// Output: "January 15, 2024"

// Check if weekend
boolean isWeekend = DateTimeUtils.isWeekend(LocalDate.now());

// Get all dates in a range
List<LocalDate> dates = DateTimeUtils.getDatesBetween(
    LocalDate.of(2024, 1, 1),
    LocalDate.of(2024, 1, 31)
);
```

## Publishing to Maven Central

### Prerequisites
1. Create account at https://s01.oss.sonatype.org/
2. Create a JIRA ticket to claim your groupId
3. Generate GPG key for signing
4. Configure credentials in `gradle.properties`

### Publish Commands
```bash
# Publish to staging repository
./gradlew publish

# Or publish and release in one step
./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
```

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.