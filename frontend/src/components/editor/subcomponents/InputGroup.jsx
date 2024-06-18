export default function InputGroup({ children, title }) {
  return (
    <div className="card card-body glass bg-secondary shadow-lg">
      {title && <div className="card-title">{title}</div>}
      {children}
    </div>
  );
}
