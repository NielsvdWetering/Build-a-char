export default function InputField({
  value,
  onChange,
  placeholder,
  className,
  ...props
}) {
  return (
    <input
      {...props}
      className={`input input-primary ${className}`}
      value={value}
      onChange={onChange}
      placeholder={placeholder}
    />
  );
}
