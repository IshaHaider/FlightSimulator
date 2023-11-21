
// Get the input fields and submit button
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const submitButton = document.getElementById('submit');

// Add event listener to the submit button
submitButton.addEventListener('click', function(event) {
  event.preventDefault(); // Prevent form submission

  // Get the values from the input fields
  const username = usernameInput.value;
  const password = passwordInput.value;

  // Perform validation
  if (username === 'admin' && password === 'password') {
    // Successful login
    alert('Login successful!');
    // Redirect to the home page or perform other actions
  } else {
    // Invalid login
    alert('Invalid username or password. Please try again.');
    // Clear the input fields
    usernameInput.value = '';
    passwordInput.value = '';
    // Set focus to the username field
    usernameInput.focus();
  }
});
