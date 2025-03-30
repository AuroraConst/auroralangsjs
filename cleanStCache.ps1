
# Run the killjava script
& "C:\dev\scripts\killjava.ps1"

# Get user profile path dynamically
$userIvyPath = Join-Path $env:USERPROFILE ".ivy2\local\org.scalablytyped\aurora-langium_sjs1_3"

# Check if the directory exists before attempting to delete
if (Test-Path $userIvyPath) {
    Write-Host "Deleting ScalablyTyped cache at $userIvyPath"
    Remove-Item $userIvyPath -Recurse -Force
    Write-Host "Cache deleted successfully"
} else {
    Write-Host "Cache directory not found at $userIvyPath"
}