#!/bin/bash

# List of directories where the command should be executed
directories=(
    "user-management"
    "task-management"
    "notification"
)

# Loop through each directory
for dir in "${directories[@]}"; do
    echo "Running ./mvnw clean package in $dir"

    # Check if directory exists
    if [ -d "$dir" ]; then
        # Navigate to the directory
        cd "$dir" || exit
        # Run the command
        ./mvnw clean package
        # Check if the command was successful
        if [ $? -eq 0 ]; then
            echo "Build succeeded in $dir"
        else
            echo "Build failed in $dir"
            exit 1 # Exit if build fails
        fi
        cd ".."
    else
        echo "Directory $dir does not exist. Skipping..."
    fi
done

docker-compose up --build
