import { useNavigate } from "react-router-dom";
import InputField from "../generic/InputField";

export default function AuthForm({
  username,
  setUsername,
  password,
  setPassword,
  onSubmit,
  submitTitle,
  redirectTitle,
  redirectURL,
}) {
  const navigate = useNavigate();

  return (
    <div className="mt-20 flex flex-col items-center gap-5">
      <InputField
        className="w-1/4"
        value={username}
        onChange={(event) => setUsername(event.target.value)}
        placeholder="username"
        onKeyUp={inputFieldKeyUp}
      />
      <InputField
        className="w-1/4"
        value={password}
        onChange={(event) => setPassword(event.target.value)}
        placeholder="password"
        type="password"
        onKeyUp={inputFieldKeyUp}
      />
      <button
        className="btn btn-primary w-52"
        onClick={onSubmit}
        disabled={
          !username ||
          username.length === 0 ||
          !password ||
          password.length === 0
        }
      >
        {submitTitle ?? "Submit"}
      </button>
      {redirectTitle && redirectURL && (
        <span
          className="cursor-pointer text-blue-800 underline hover:text-purple-600"
          onClick={() => navigate(redirectURL)}
        >
          {redirectTitle}
        </span>
      )}
    </div>
  );

  function inputFieldKeyUp(event) {
    if (event.key === "Enter") {
      onSubmit();
    }
  }
}
