# Tailwind implementation

## Status
Accepted

## Context
Tailwind provides default classes to remove the need for CSS files. This change, however, also makes elements with very long className values, as well as causing DRY issues when multiple elements have the same combination of tailwind classes.

## Decision
Commonly occuring tailwind classes will be centered into global styles. Duplicate code will be prevented by combining similar elements into single components.

## Consequences
More components will mean a more complex file structure. However, this change will enable fast styling for elements without breaking code conventions