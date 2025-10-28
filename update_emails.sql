-- Update email addresses
UPDATE users SET email = 'admin@college.com' WHERE email = 'admin@college.edu';
UPDATE users SET email = 'student1@college.com' WHERE email = 'john.doe@student.edu';

-- Verify the changes
SELECT id, name, email, role FROM users WHERE email IN ('admin@college.com', 'student1@college.com');