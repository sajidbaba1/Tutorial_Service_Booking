Steps Documentation Index

How to use this folder
- Each step has its own folder: step-XX_title
- Inside each folder, open README.txt on your phone to follow the exact actions.
- We will always add/update the step README before every commit and push.

Current Steps
- step-01_initial-setup-and-serviceitem
  - Setup Spring Boot 3 + MySQL
  - Health endpoint
  - ServiceItem create/list feature with validation

Commit & Push Workflow (run from project root)
1) Stage all changes
   git add .

2) Commit with a clear message (example for step 01)
   git commit -m "feat(step-01): initial setup + health + ServiceItem create/list with validation"

3) If first push, set main branch and origin (replace <YOUR_REPO_URL>)
   git branch -M main
   git remote add origin <YOUR_REPO_URL>

4) Push to GitHub (force only if you want to overwrite remote main)
   git push -u origin main --force

Note
- Replace <YOUR_REPO_URL> with your GitHub repo: e.g., https://github.com/USERNAME/ServiceBookingSystem.git
- Use --force with care; it rewrites remote history.
