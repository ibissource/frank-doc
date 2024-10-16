export interface FrankDocument {
  metadata: Metadata;
  types: TypeElement[];
  elements: Element[];
  enums: Enum[];
  labels: Label[];
  properties: Property[];
}

export type Element = {
  name: string;
  fullName: string;
  abstract?: boolean;
  description?: string;
  parent?: string;
  elementNames: string[];
  attributes?: Attribute[];
  children?: Child[];
  forwards?: ParsedTag[];
  deprecated?: DeprecationInfo;
  parameters?: ParsedTag[];
  parametersDescription?: string;
};

export type Attribute = {
  name: string;
  mandatory?: boolean;
  describer?: string;
  description?: string;
  type?: string;
  default?: string;
  deprecated?: DeprecationInfo;
  enum?: string;
};

export type Child = {
  multiple: boolean;
  roleName: string;
  description?: string;
  type?: string;
  deprecated?: boolean;
  mandatory?: boolean;
};

export type ParsedTag = {
  name: string;
  description?: string;
};

export type Enum = {
  name: string;
  values: Value[];
};

export type Value = {
  label: string;
  description?: string;
  deprecated?: boolean;
};

export type Group = {
  name: string;
  types: string[];
};

export type Metadata = {
  version: string;
};

export type TypeElement = {
  name: string;
  members: string[];
};

export type DeprecationInfo = {
  forRemoval: boolean;
  since?: string;
  description?: string;
};

export type Label = {
  label: string;
  values: string[];
};

export type Property = {
  name: string;
  properties: Record<string, string>;
};
