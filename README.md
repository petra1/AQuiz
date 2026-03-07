Description of the Test Procedure 

To verify the consistency of results produced by automated accessibility tools, a dedicated application titled ‘Quiz’ was developed. This app features six questions regarding the Web Content Accessibility Guidelines (WCAG), each with four multiple-choice answers.
The application was engineered so that twelve different variants could theoretically be generated with minimal effort—simply by toggling code comments—without altering the app's core functionality. Only the layout undergoes slight modifications between versions.
During development, accessibility was deliberately neglected to ensure the presence of the following issues:
Insufficient contrast in content.
Lack of alternative text for the logo.
Incomplete keyboard navigation (not all content is accessible).
Responsive design failures: content becomes inaccessible when zoomed to 200%.
Screen reader issues: TalkBack provides no feedback after a user answers a question.
Heading not semantically marked as such
Conducting the Tests
For the testing phase, five specific variants of the app are generated. Each variant is evaluated using three different automated accessibility testing tools. The results are then compared to determine the level of consistency between the tools.
Data Collection
The following data points are documented:
Test Matrix: Variant × Tool × Error Type.
Consistency Rate per error type and tool.
Documentation of all identified inconsistencies.
Organisation of the Archive
‘Original’ Directory: Contains the baseline version of the app as a reference.
Variant Directories: Each contains an ‘apk’ folder with the executable (.apk) file for that specific version.
Documentation: Each directory includes a description of the modifications made to that specific variant, accompanied by screenshots to illustrate the changes.
