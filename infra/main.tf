terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"

    }

  }
}

provider "aws" {
  region = "us-east-1"
}

resource "aws_security_group" "securitygroup_dio_week" {

  name        = "securitygroup_dio_week"
  description = "Permite acesso Http e permite acesso a internet"

  #  Regra de entrada do grupo. No caso do 0.0.0.0/0 permite que qualquer requeste passe pelo grupo de segurança da porta 80 para 80. Ou seja so a 80
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Regra de sai:
  egress {
    from_port   = 0
    to_port     = 65535
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_key_pair" "key-pair"{
  key_name = "terraform_keypair"
  public_key = file("C:\\Users\\mathe\\.ssh\\id_rsa.pub")
}

# definir instancia da ec2
resource "aws_instance" "server_dio_week" {

  ami                    = "ami-066784287e358dad1"
  instance_type          = "t2.nano"
  user_data              = file("user_data.sh")
  key_name = aws_key_pair.key-pair.key_name
  vpc_security_group_ids = [aws_security_group.securitygroup_dio_week.id]

}
