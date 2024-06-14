import InputField from "../generic/InputField";

export default function AuthForm({
  username,
  setUsername,
  password,
  setPassword,
  onSubmit,
  submitTitle,
}) {
  return (
    <div className="mt-20 flex flex-col items-center gap-10">
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
        className="btn btn-primary"
        onClick={submit}
        disabled={
          !username ||
          username.length === 0 ||
          !password ||
          password.length === 0
        }
      >
        {submitTitle ?? "Submit"}
      </button>
    </div>
  );

  function inputFieldKeyUp(event) {
    if (event.key === "Enter") {
      submit();
    }
  }

  function submit() {
    onSubmit?.({ username, password });
  }
}
