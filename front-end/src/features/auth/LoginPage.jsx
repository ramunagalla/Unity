import { useState } from "react";
import { useNavigate } from "react-router-dom";
import authService from "../../services/authService";
import "../../css/LoginPage.css"; // Import component-specific CSS

const LoginPage = () => {
  const [credentials, setCredentials] = useState({ username: "", password: "" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
        const response = await authService.login(credentials);
        const user = response.data; // Extracting the user object

        if (!user || !user.role) {
            throw new Error("Invalid response from server");
        }

        // Store user details in localStorage
        localStorage.setItem("user", JSON.stringify(user));

        // Redirect based on role
        navigate(user.role === "ADMIN" ? "/admin-dashboard" : "/dashboard");
    } catch (err) {
        setError(err.response?.data || "Invalid login credentials");
    }
};


  return (
    <div className="login-container">
      <div className="login-box">
        <h2 className="login-heading">Sign in to Unity Bank</h2>

        {error && <p className="text-red-600 text-sm text-center mb-3">{error}</p>}

        <form onSubmit={handleSubmit} className="space-y-3">
          <div>
            <label className="block font-medium text-white">Username</label>
            <input type="text" name="username" value={credentials.username} onChange={handleChange} className="login-input" required />
          </div>

          <div>
            <label className="block font-medium text-white">Password</label>
            <input type="password" name="password" value={credentials.password} onChange={handleChange} className="login-input" required />
          </div>

          <button type="submit" className="login-btn">Login</button>
        </form>

        <div className="text-center mt-3">
          <p className="text-white text-sm">
            Don't have an account?{" "}
            <span onClick={() => navigate("/register")} className="login-link">
              Create an account
            </span>
          </p>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
