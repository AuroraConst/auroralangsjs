#!/bin/bash

# Kill Java-related processes directly
echo "Killing jqs, java, and esbuild processes..."
pkill -f jqs 2>/dev/null
pkill -f java 2>/dev/null
pkill -f esbuild 2>/dev/null
echo "Processes terminated (if running)."

# Path to ScalablyTyped Ivy cache
USER_IVY_PATH="$HOME/.ivy2/local/org.scalablytyped/aurora-langium_sjs1_3"

# Check if the directory exists before attempting to delete
if [ -d "$USER_IVY_PATH" ]; then
    echo "Deleting ScalablyTyped cache at $USER_IVY_PATH"
    rm -rf "$USER_IVY_PATH"
    echo "Cache deleted successfully"
else
    echo "Cache directory not found at $USER_IVY_PATH"
fi

# Clean project-related directories
echo "Cleaning build-related directories..."
rm -rf .bloop
rm -rf .metals
rm -rf .bsp
rm -rf project/.bloop
rm -rf project/target
rm -rf target
echo "Project cleanup completed."