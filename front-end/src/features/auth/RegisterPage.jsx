import { useState } from "react";
import { useNavigate } from "react-router-dom";
import authService from "../../services/authService";
import "../../css/RegisterPage.css"; // Import CSS file

const RegisterPage = () => {
  const [formData, setFormData] = useState({
    username: "",
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    phoneNumber: "",
    email: "",
    password: "",
  });

  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      await authService.register(formData);
      navigate("/login"); // Redirect to login after successful registration
    } catch (err) {
      setError(err.response?.data?.message || "Registration failed.");
    }
  };

  return (
    <div className="register-container">
      <div className="register-box">
        <h2 className="register-heading">Create an Account</h2>

        {error && <p className="register-error">{error}</p>}

        <form onSubmit={handleSubmit} className="space-y-3">
          <div>
            <label className="register-label">Username</label>
            <input type="text" name="username" value={formData.username} onChange={handleChange} className="register-input" required />
          </div>

          <div>
            <label className="register-label">First Name</label>
            <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} className="register-input" required />
          </div>

          <div>
            <label className="register-label">Last Name</label>
            <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} className="register-input" required />
          </div>

          <div>
            <label className="register-label">Date of Birth</label>
            <input type="date" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleChange} className="register-input" required />
          </div>

          <div>
            <label className="register-label">Phone Number</label>
            <input type="tel" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} className="register-input" required pattern="[0-9]{10}" />
          </div>

          <div>
            <label className="register-label">Email</label>
            <input type="email" name="email" value={formData.email} onChange={handleChange} className="register-input" required />
          </div>

          <div>
            <label className="register-label">Password</label>
            <input type="password" name="password" value={formData.password} onChange={handleChange} className="register-input" required minLength="6" />
          </div>

          <button type="submit" className="register-button">Register</button>
        </form>

        <div className="text-center mt-3">
          <p className="text-white text-sm">
            Already have an account?{" "}
            <span onClick={() => navigate("/login")} className="register-link">
              Login here
            </span>
          </p>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;
